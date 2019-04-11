/*
 * *
 *  * Copyright (c) 2016, China Construction Bank Co., Ltd. All rights reserved.
 *  * 中国建设银行版权所有.
 *  * <p/>
 *  * 审核人：
 *
 */

package com.threewater.webserver.webtemplate.util.sequence.twitter;


import com.threewater.webserver.webtemplate.util.sequence.Sequence;
import org.springframework.stereotype.Component;

@Component
public class SnowFlakeSequence implements Sequence {

    /**
     * 开始时间截 (2018-05-01)
     */
    private final long twEpoch = 1525104000000L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 2L;

    /**
     * 数据中心标识id所占的位数
     */
    private final long dataCenterIdBits = 1L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    private String id;

    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     * @return 对象
     */
    public SnowFlakeSequence(String id, long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenterId Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.id = id;
    }

    public SnowFlakeSequence(){

    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public synchronized long nextValue() {
        long timestamp = currentTimeMillis();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
                sequence = 0L;
                }

                //上次生成ID的时间截
                lastTimestamp = timestamp;

                //移位并通过或运算拼到一起组成64位的ID
                return ((timestamp - twEpoch) << timestampShift) //
                | (dataCenterId << dataCenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
                }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    private static SnowFlakeSequence seq = new SnowFlakeSequence();

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());

        Sequence sequence = new SnowFlakeSequence("", 3, 1);

        System.out.println(sequence.id());
        System.out.println(sequence.nextValue());
        for (int i = 0; i < 1000000; i++) {
            System.out.println(sequence.nextValue());
        }
//        System.out.println(sequence.nextValue());
    }

    public static String genPKId(){
        return String.valueOf(seq.nextValue());
    }


}
