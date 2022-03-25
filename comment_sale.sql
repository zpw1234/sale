/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : comment_sale

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 25/03/2022 10:38:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `amount` int NOT NULL COMMENT '订单总金额',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '订单状态，默认为新下单',
  `is_pay` tinyint NOT NULL DEFAULT 0 COMMENT '支付状态，默认未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------

-- ----------------------------
-- Table structure for account_detail
-- ----------------------------
DROP TABLE IF EXISTS `account_detail`;
CREATE TABLE `account_detail`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `account_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容标题',
  `price` decimal(10, 2) NOT NULL COMMENT '内容单价',
  `num` int NOT NULL COMMENT '数量',
  `icon` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容图标',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_account_id`(`account_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_detail
-- ----------------------------
INSERT INTO `account_detail` VALUES ('1681c1e141ad45cd90a185da785d60df', '596c939c9b7b4b068228824e196240bf', '1552623648237651760', '索尼PS', 3000.00, 2, 'https://2c.zol-img.com.cn/product/150_320x240/206/cebShCGbF6OBI.jpg', '2022-03-11 12:02:21', '2022-03-11 12:02:21', '1549943343592886554', 'buyer');
INSERT INTO `account_detail` VALUES ('413d43700aab47dc813c6813641532ff', 'a50667d2185143d6b9e786b4a23659a7', '1552623648237651760', '索尼PS', 3000.00, 3, 'https://2c.zol-img.com.cn/product/150_320x240/206/cebShCGbF6OBI.jpg', '2022-03-11 20:26:51', '2022-03-11 20:26:51', '1549943343592886554', 'buyer');
INSERT INTO `account_detail` VALUES ('a3ec06c1bf1644f89ca05475c2296b3f', '596c939c9b7b4b068228824e196240bf', '1551747822940741268', '蛋糕', 128.00, 1, 'https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=d55eea50ad4bd11310c0bf603bc6cf6a/023b5bb5c9ea15ce2b18ee2ab6003af33a87b21a.jpg', '2022-03-11 12:02:21', '2022-03-11 12:02:21', '1549943343592886554', 'buyer');
INSERT INTO `account_detail` VALUES ('b595325a5d3540d0bf7e533d5211d8f6', '9295af5bdacc42679ef9ab8282ee1187', '7d9d3898a9824a6f9aff2498a3cb6bec', '安全帽', 3.00, 3, 'http://r7usvqfl8.hd-bkt.clouddn.com/08b79286-6640-4e86-b1f2-ed87952cc7f9.jpg', '2022-03-11 21:06:46', '2022-03-11 21:06:46', '1549943343592886554', 'buyer');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `num` int NOT NULL COMMENT '数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('61ac7437fe8e4e59bc50e9cbc8712acc', '1549943343592886554', '7d9d3898a9824a6f9aff2498a3cb6bec', 1, '2022-03-11 21:06:31', '2022-03-11 21:06:31');
INSERT INTO `cart` VALUES ('a450cded6e6347bc9e6562077a545db9', '1549943343592886554', '1551747822940741268', 1, '2022-03-10 20:30:22', '2022-03-10 20:30:22');

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容发布者id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容标题',
  `summary` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容摘要',
  `image` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `price` int NULL DEFAULT NULL COMMENT '购买时的价格（单位：分）',
  `text` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
  `num` int NULL DEFAULT 0 COMMENT '卖出数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int NOT NULL DEFAULT 0 COMMENT '1为购买，0为未购买',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('1551747822940741268', '1549943343592886551', '蛋糕', '水果蛋糕', 'https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=d55eea50ad4bd11310c0bf603bc6cf6a/023b5bb5c9ea15ce2b18ee2ab6003af33a87b21a.jpg', 128, '富含草莓、蓝莓、黄桃等', 0, '2022-03-11 19:58:00', '2022-03-11 19:58:00', 1);
INSERT INTO `content` VALUES ('1552623648237651760', '1549943343592886551', '索尼PS', '绝版', 'https://2c.zol-img.com.cn/product/150_320x240/206/cebShCGbF6OBI.jpg', 3000, 'PlayStation 4是索尼电脑娱乐公司推出的家用游戏机。是PlayStation游戏机系列的第四代游戏主机，采用AMD Jaguar 8core处理器。', 17, '2022-03-11 20:26:51', '2022-03-11 20:26:51', 1);
INSERT INTO `content` VALUES ('1a8d3a6d0d8c4cdd813c33cc0e9d119f', '1549943343592886551', '测试', '测试你', 'http://r7usvqfl8.hd-bkt.clouddn.com/d4fcff95-e389-4a28-80c9-f6112db6083d.png', 1, '带带我', 20, '2022-03-11 20:01:11', '2022-03-11 20:01:11', 1);
INSERT INTO `content` VALUES ('7d9d3898a9824a6f9aff2498a3cb6bec', '1549943343592886551', '安全帽', '安全帽测试', 'http://r7usvqfl8.hd-bkt.clouddn.com/08b79286-6640-4e86-b1f2-ed87952cc7f9.jpg', 3, '作为安全帽测试的图片', 19, '2022-03-11 21:06:46', '2022-03-11 21:06:46', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_buyer` tinyint NOT NULL COMMENT '0为买家，1为卖家',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1549943343592886551', 'seller', '30ef2809b8914ebe34e2869dda3880ee', 0, '2022-03-09 14:58:20', '2022-03-09 14:58:20');
INSERT INTO `user` VALUES ('1549943343592886554', 'buyer', '1960eb643d8ac2598dc07940b403c2cc', 1, '2022-03-09 14:57:55', '2022-03-09 14:57:55');

SET FOREIGN_KEY_CHECKS = 1;
