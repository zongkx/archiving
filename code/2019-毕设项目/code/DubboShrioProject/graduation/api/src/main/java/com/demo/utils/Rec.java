package com.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.entity.UserSoftwareItems;

import java.util.*;

public class Rec {
	private static final Logger log = LoggerFactory.getLogger(Rec.class);

	public static Map<Long, Double> cf(List<UserSoftwareItems> UserSoftwareItemsList, String recommendUser,
			int userTop) {
		
		
		int N = UserSoftwareItemsList.size();
		/** 建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】 */

		int[][] sparseMatrix = new int[N][N];
		/** 存储每一个用户对应的不同软件分享总数 eg: A 3 */

		Map<String, Integer> userItemLength = new HashMap<>();
		/** 建立软件分享到用户的倒排表 eg: a A B */

		Map<Long, Set<String>> itemUserCollection = new HashMap<>();
		/** 辅助存储软件分享集合 */

		Set<Long> items = new HashSet<>();
		/** 辅助存储每一个用户的用户ID映射 */

		Map<String, Integer> userID = new HashMap<>();
		/** 辅助存储每一个ID对应的用户映射 */

		Map<Integer, String> idUser = new HashMap<>();
		/** 存储目标用户与所有用户相似度 */
		Map<String, Double> similarityMap = new LinkedHashMap<>();
		int i = 0;
		for (UserSoftwareItems userSoftwareItems : UserSoftwareItemsList) {
			Long[] user_item = new Long[userSoftwareItems.getSoftwareId().size()];
			user_item = userSoftwareItems.getSoftwareId().toArray(user_item);
			int length = user_item.length;
			/** 例如: A 3 */
			userItemLength.put(userSoftwareItems.getNickname(), length);
			/** 用户ID与稀疏矩阵建立对应关系 */
			userID.put(userSoftwareItems.getNickname(), i);
			idUser.put(i, userSoftwareItems.getNickname());
			/**
			 * 建立软件分享--用户倒排表
			 */
			for (int j = 0; j < length; j++) {
				/** 如果已经包含对应的软件分享--用户映射，直接添加对应的用户 */
				if (items.contains(user_item[j])) {
					itemUserCollection.get(user_item[j]).add(userSoftwareItems.getNickname());
					/** 否则创建对应软件分享--用户集合映射 */
				} else {
					items.add(user_item[j]);
					/** 创建软件分享--用户倒排关系 */
					itemUserCollection.put(user_item[j], new HashSet<String>());
					itemUserCollection.get(user_item[j]).add(userSoftwareItems.getNickname());
				}
			}
			i++;
		}
		/**
		 * 计算相似度矩阵【稀疏】
		 */
		Set<Map.Entry<Long, Set<String>>> entrySet = itemUserCollection.entrySet();
		Iterator<Map.Entry<Long, Set<String>>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Set<String> commonUsers = iterator.next().getValue();
			for (String user_u : commonUsers) {
				for (String user_v : commonUsers) {
					if (user_u.equals(user_v)) {
						continue;
					}
					/** 计算用户u与用户v都有正反馈的软件分享总数 */
					sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;
				}
			}
		}
		/**
		 * 计算用户之间的相似度【余弦相似性】
		 */
		int recommendUserId = userID.get(recommendUser);
		for (int j = 0; j < sparseMatrix.length; j++) {
			if (j != recommendUserId) {
				double temp = sparseMatrix[recommendUserId][j] / Math
						.sqrt(userItemLength.get(idUser.get(recommendUserId)) * userItemLength.get(idUser.get(j)));
				log.info(idUser.get(recommendUserId) + "--" + idUser.get(j) + "相似度:" + temp);
				similarityMap.put(idUser.get(j), temp);
			}
		}
		/**
		 * 用户相似度排序
		 */
		List<Map.Entry<String, Double>> list = new ArrayList<>(similarityMap.entrySet());
		list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
		similarityMap.clear();
		for (Map.Entry<String, Double> mapEntry : list) {
			similarityMap.put(mapEntry.getKey(), mapEntry.getValue());
		}
		@SuppressWarnings("rawtypes")
		Iterator it = similarityMap.entrySet().iterator();
		List<String> topN = new ArrayList<>();
		int num = 0;
		while (it.hasNext()) {
			if (num >= userTop) {
				break;
			}
			@SuppressWarnings("rawtypes")
			Map.Entry entity = (Map.Entry) it.next();
			topN.add((String) entity.getKey());
			num++;
		}
		Map<Long, Double> results = new HashMap<>(8);
		/**
		 * 计算指定用户recommendUser的软件分享推荐度
		 */
		/**
		 * 遍历每一件软件分享
		 */

		for (Long item : items) {
			/** 得到购买当前软件分享的所有用户集合 */
			Set<String> users = itemUserCollection.get(item);
			/** 如果被推荐用户没有评分当前软件分享，则进行推荐度计算 */
			if (!users.contains(recommendUser)) {
				double itemRecommendDegree = 0.0;
				for (String user : users) {
					/** 推荐度计算 */
					if (topN.contains(user)) {
						itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)]
								/ Math.sqrt(userItemLength.get(recommendUser) * userItemLength.get(user));
					}
				}
				log.info("The item " + item + " for " + recommendUser + "'s recommended degree:" + itemRecommendDegree);
				results.put(item, itemRecommendDegree);
			}
		}
		return results;
	}
}
