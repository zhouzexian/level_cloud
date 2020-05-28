package com.joey.cloud.flowable.utils;


import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 流程工具类
 * @author joey
 */
@Slf4j
@Component
public class FlowableUtil {
    /**
     * 流程运行控制服务
     */
    @Resource
    private RuntimeService runtimeService;

    /**
     * 任务管理服务
     */
    @Resource
    private TaskService taskService;

    /**
     * 流程引擎
     */
    @Resource
    private ProcessEngine processEngine;

    public RuntimeService getRuntimeService() {
        return runtimeService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public ProcessEngine getProcessEngine() {
        return processEngine;
    }

    /**
     * 启动流程
     *
     * @param processKey  流程定义key(流程图ID)
     * @param businessKey 业务key
     * @param map         参数键值对
     * @return 流程实例ID
     * @Author Joey
     */
    public String start(String processKey, String businessKey, HashMap<String, Object> map) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, businessKey, map);
        return processInstance.getId();
    }

    /**
     * 终止流程
     *
     * @param processInstanceId 流程实例ID
     * @param reason            终止理由
     * @Author Joey
     */
    public void stop(String processInstanceId, String reason) {
        runtimeService.deleteProcessInstance(processInstanceId, reason);
    }


    /**
     * 获取指定用户的任务列表（创建时间倒序）
     *
     * @param userId 用户ID
     * @return 任务列表
     * @Author Joey
     */
    public List<Task> getListByUserId(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        return tasks;
    }

    /**
     * 获取指定用户组的任务列表
     *
     * @param group 用户组
     * @return 任务列表
     * @Author Joey
     */
    public List<Task> getListByGroup(String group) {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(group).orderByTaskCreateTime().desc().list();
        return tasks;
    }


    /**
     * 完成指定任务
     *
     * @param taskId 任务ID
     * @param map    变量键值对 outcome:YES=通过NO=驳回
     * @Author Joey
     */
    public void complete(String taskId, HashMap<String, Object> map) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            log.error("流程不存在,taskId="+taskId);
        }else {
            taskService.complete(taskId, map);
        }
    }

    /**
     * 获取指定任务列表中的特定任务
     *
     * @param list        任务列表
     * @param businessKey 业务key
     * @return 任务
     * @Author Joey
     */
    public Task getOneByBusinessKey(List<Task> list, String businessKey) {
        Task task = null;
        for (Task t : list) {
            // 通过任务对象获取流程实例
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).singleResult();
            if (businessKey.equals(pi.getBusinessKey())) {
                task = t;
                break;
            }
        }
        return task;
    }

    /**
     * 创建流程并完成第一个任务
     *
     * @param processKey  流程定义key(流程图ID)
     * @param businessKey 业务key
     * @param map         变量键值对
     * @Author Joey
     */
    public void startAndComplete(String processKey, String businessKey, HashMap<String, Object> map) {
        String processInstanceId = start(processKey, businessKey, map);
        Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.complete(task.getId(), map);

    }

    /**
     * 退回到指定任务节点
     *
     * @param currentTaskId 当前任务ID
     * @param targetTaskKey 目标任务节点key
     * @Author Joey
     */
    public void backToStep(String currentTaskId, String targetTaskKey) {

        Task currentTask = taskService.createTaskQuery().taskId(currentTaskId).singleResult();
        if (currentTask == null) {
            log.error("当前任务节点不存在,taskId="+currentTaskId);
        }
        List<String> currentTaskKeys = new ArrayList<>();
        currentTaskKeys.add(currentTask.getTaskDefinitionKey());
        runtimeService.createChangeActivityStateBuilder().processInstanceId(currentTask.getProcessInstanceId()).moveActivityIdsToSingleActivityId(currentTaskKeys, targetTaskKey);
    }
}
