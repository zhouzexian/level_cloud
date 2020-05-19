package com.joey.cloud.common.core.utils;


/**
 * 文件目录打散工具类
 * @author joey
 */
public class DirUtil {
    public static String SPITS_STR2="/";


    public static void main(String[] args) {
        Long fileId=88888888L;
        System.out.println(getRandomDir(fileId));
    }
    /**
     * 实现两级目录
     * @param fileId
     * @return 二级目录  如：/2/4
     */
    public static String getRandomDir(Long fileId){
        // 获取唯一文件名的hashcode值
        int hashcode = fileId.hashCode();
        // 和0xf进行&操作
        int dir1 = hashcode & 0xf;
        // 先右移4位，再和0xf进行&操作
        int dir2 = (hashcode >>> 4) & 0xf;
        String result = SPITS_STR2 + dir1 + SPITS_STR2 + dir2+SPITS_STR2;
        return result;
    }
}
