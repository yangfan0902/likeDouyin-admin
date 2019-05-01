package com.imooc.web.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKcurator {
	private CuratorFramework client = null;
	final static Logger log = LoggerFactory.getLogger(ZKcurator.class);

	public ZKcurator(CuratorFramework client) {
		this.client = client;
	}

	public void init() {
		client = client.usingNamespace("admin");

		try {
			// 判断在admin命名空间下是否有bgm结点
			if (client.checkExists().forPath("/bgm") == null) {
				client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT) // 结点类型：持久
						.withACL(Ids.OPEN_ACL_UNSAFE) // 权限
						.forPath("/bgm");

				log.info("zk客户端初始化成功");

				log.info("zk服务器状态: " + client.isStarted());
			}
		} catch (Exception e) {
			log.error("zookeeper客户端连接初始化错误");
			e.printStackTrace();
		}
	}

	/**
	 * 增加或者删除bgm,向zk创建子节点，供小程序端监听
	 * 
	 * @param bgmId
	 * @param operObject
	 */
	public void sendBgmOperator(String bgmId, String operObject) {
		try {
			client.create().creatingParentsIfNeeded()
					.withMode(CreateMode.PERSISTENT) // 结点类型：持久
					.withACL(Ids.OPEN_ACL_UNSAFE) // 权限
					.forPath("/bgm/"+bgmId,operObject.getBytes());

			log.info("zk客户端初始化成功");

			log.info("zk服务器状态: " + client.isStarted());

		} catch (Exception e) {
			log.error("zookeeper客户端连接初始化错误");
			e.printStackTrace();
		}
	}
}
