/**
 * 
 */
package redis;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import config.RedisPool;
import constant.RedisConst;

/**
 * 并发下，DB缓存一致性问题案例演示
 * 
 * @author chenzhao @date May 31, 2019
 */
public class UpdateDbAndCache2 {

	static ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	static {
		executor.setMaxPoolSize(20);
		executor.setCorePoolSize(10);
		executor.setQueueCapacity(400);
		executor.setThreadNamePrefix("ThreadPoolTaskExecutor");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		executor.initialize();
	}

	public static void main(String[] args) throws InterruptedException {
		RedisPool.set(RedisConst.mysql_SoR_OrderNum, 1);// v1
		RedisPool.set(RedisConst.redisCache_OrderNum, RedisPool.get(RedisConst.mysql_SoR_OrderNum));// v1

		// 获取DB中最新数据，同步缓存
		RedisPool.incr(RedisConst.mysql_SoR_OrderNum);// v2
		RedisPool.set(RedisConst.redisCache_OrderNum, RedisPool.get(RedisConst.mysql_SoR_OrderNum));// v2
		System.out.println("#redisCache_OrderNum:" + RedisPool.get(RedisConst.redisCache_OrderNum));
		System.out.println("#mysql_SoR_OrderNum:" + RedisPool.get(RedisConst.mysql_SoR_OrderNum));
	}

}
