/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `test`;

/*Table structure for table `mac_attrname` */

DROP TABLE IF EXISTS `mac_attrname`;

CREATE TABLE `mac_attrname` (
  `attrNameId` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品属性名编号',
  `attrName` varchar(50) NOT NULL COMMENT '属性名（颜色、尺寸、容量...）',
  PRIMARY KEY (`attrNameId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `mac_attrname` */

insert  into `mac_attrname`(`attrNameId`,`attrName`) values (1,'颜色'),(2,'尺寸'),(3,'容量'),(4,'尺码');

/*Table structure for table `mac_attrvalue` */

DROP TABLE IF EXISTS `mac_attrvalue`;

CREATE TABLE `mac_attrvalue` (
  `attrValueId` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品属性值编号',
  `attrValue` varchar(50) NOT NULL COMMENT '属性值（黑色、白色、X、XL...）',
  `attrNameId` int(11) NOT NULL COMMENT '商品属性名编号(外键)',
  PRIMARY KEY (`attrValueId`),
  KEY `attrNameId` (`attrNameId`),
  CONSTRAINT `mac_attrvalue_ibfk_1` FOREIGN KEY (`attrNameId`) REFERENCES `mac_attrname` (`attrNameId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `mac_attrvalue` */

insert  into `mac_attrvalue`(`attrValueId`,`attrValue`,`attrNameId`) values (1,'黑色',1),(2,'白色',1),(3,'粉色',1),(4,'灰色',1),(5,'红色',1),(6,'黄色',1),(7,'绿色',1),(8,'蓝色',1),(9,'紫色',1),(10,'棕色',1),(11,'花色',1),(12,'透明',1),(13,'S',2),(14,'M',2),(15,'L',2),(16,'XL',2),(17,'XXL',2),(18,'XXXL',2),(19,'26',4),(20,'27',4),(21,'28',4),(22,'29',4),(23,'30',4),(24,'31',4),(25,'32',4),(26,'33',4),(27,'34',4),(28,'35',4),(29,'36',4),(30,'37',4),(31,'38',4),(32,'39',4),(33,'40',4);

/*Table structure for table `mac_discount` */

DROP TABLE IF EXISTS `mac_discount`;

CREATE TABLE `mac_discount` (
  `discountId` int(11) NOT NULL AUTO_INCREMENT COMMENT '折扣编号',
  `goodsId` int(11) NOT NULL COMMENT '商品Id',
  `discountNum` int(11) NOT NULL COMMENT '折扣',
  `startTime` date NOT NULL COMMENT '折扣开始时间',
  `endTime` date NOT NULL COMMENT '折扣结束时间',
  PRIMARY KEY (`discountId`),
  KEY `goodsId` (`goodsId`),
  CONSTRAINT `mac_discount_ibfk_1` FOREIGN KEY (`goodsId`) REFERENCES `mac_goods` (`goodsId`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8;

/*Data for the table `mac_discount` */

insert  into `mac_discount`(`discountId`,`goodsId`,`discountNum`,`startTime`,`endTime`) values (1001,1001,8,'2019-01-09','2019-01-31'),(1002,1001,6,'2019-01-13','2019-01-14');

/*Table structure for table `mac_goods` */

DROP TABLE IF EXISTS `mac_goods`;

CREATE TABLE `mac_goods` (
  `goodsId` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `typeId` int(11) NOT NULL COMMENT '商品类别ID（外键）',
  `goodsName` varchar(50) NOT NULL COMMENT '商品名称',
  `price` double DEFAULT NULL COMMENT '价格',
  `addTimes` date NOT NULL COMMENT '上架时间',
  `supSales` int(11) DEFAULT NULL COMMENT 'SUP销量',
  `commentNum` int(11) DEFAULT NULL COMMENT '评论数',
  `defaultPhotoUrl` varchar(255) DEFAULT NULL COMMENT '商品图片默认资源路径',
  `detailsPhotoUrl` varchar(255) DEFAULT NULL COMMENT '详细商品图片资源路径',
  PRIMARY KEY (`goodsId`),
  KEY `typeId` (`typeId`),
  CONSTRAINT `mac_goods_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `mac_goodstype` (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=1017 DEFAULT CHARSET=utf8;

/*Data for the table `mac_goods` */

insert  into `mac_goods`(`goodsId`,`typeId`,`goodsName`,`price`,`addTimes`,`supSales`,`commentNum`,`defaultPhotoUrl`,`detailsPhotoUrl`) values (1001,17,'森系小清新四件套',300,'2018-12-31',180,NULL,'/res/static/img/paging_img2.jpg','/res/static/img/details_imgbig.jpg'),(1002,17,'森系小清新四件套',300,'2018-12-31',180,NULL,'/res/static/img/paging_img2.jpg','/res/static/img/details_imgbig.jpg'),(1003,17,'森系小清新四件套',300,'2018-12-31',180,NULL,'/res/static/img/paging_img2.jpg','/res/static/img/details_imgbig.jpg'),(1004,17,'森系小清新四件套',300,'2018-12-31',230,NULL,'/res/static/img/paging_img2.jpg','/res/static/img/details_imgbig.jpg'),(1005,17,'森系小清新四件套',300,'2018-12-31',220,NULL,'/res/static/img/paging_img2.jpg','/res/static/img/details_imgbig.jpg'),(1006,17,'森系小清新四件套',300,'2018-12-31',210,NULL,'/res/static/img/paging_img2.jpg','/res/static/img/details_imgbig.jpg'),(1007,17,'森系小清新四件套',300,'2018-12-31',200,NULL,'/res/static/img/paging_img2.jpg','/res/static/img/details_imgbig.jpg'),(1008,17,'森系小清新四件套',300,'2018-12-31',190,NULL,'/res/static/img/paging_img2.jpg','/res/static/img/details_imgbig.jpg'),(1009,17,'森系小清新四件套',300,'2018-12-31',180,NULL,'/res/static/img/paging_img2.jpg','/res/static/img/details_imgbig.jpg'),(1010,17,'森系小清新四件套',300,'2018-12-31',170,NULL,'/res/static/img/paging_img2.jpg','/res/static/img/details_imgbig.jpg'),(1011,7,'宝宝专用硅胶环保饭碗四套+调羹+筷子自助学吃饭套装',180,'2018-12-31',170,NULL,'/res/static/img/tuan_img1.jpg','/res/static/img/details_imgbig.jpg'),(1012,7,'宝宝专用硅胶环保饭碗四套+调羹+筷子自助学吃饭套装',180,'2018-12-31',170,NULL,'/res/static/img/tuan_img1.jpg','/res/static/img/details_imgbig.jpg'),(1013,7,'宝宝专用硅胶环保饭碗四套+调羹+筷子自助学吃饭套装',180,'2018-12-31',170,NULL,'/res/static/img/tuan_img1.jpg','/res/static/img/details_imgbig.jpg'),(1014,7,'宝宝专用硅胶环保饭碗四套+调羹+筷子自助学吃饭套装',180,'2018-12-31',170,NULL,'/res/static/img/tuan_img1.jpg','/res/static/img/details_imgbig.jpg'),(1015,7,'宝宝专用硅胶环保饭碗四套+调羹+筷子自助学吃饭套装',180,'2018-12-31',170,NULL,'/res/static/img/tuan_img1.jpg','/res/static/img/details_imgbig.jpg');

/*Table structure for table `mac_goods_attr_conn` */

DROP TABLE IF EXISTS `mac_goods_attr_conn`;

CREATE TABLE `mac_goods_attr_conn` (
  `goodsAttrConnId` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品与属性关系编号',
  `goodsId` int(11) NOT NULL COMMENT '商品编号（外键）',
  `attrNameId` int(11) NOT NULL COMMENT '商品属性名编号（外键）',
  `attrValueId` int(11) NOT NULL COMMENT '商品属性值编号（外键）',
  PRIMARY KEY (`goodsAttrConnId`),
  KEY `goodsId` (`goodsId`),
  KEY `attrNameId` (`attrNameId`),
  KEY `attrValueId` (`attrValueId`),
  CONSTRAINT `mac_goods_attr_conn_ibfk_1` FOREIGN KEY (`goodsId`) REFERENCES `mac_goods` (`goodsId`),
  CONSTRAINT `mac_goods_attr_conn_ibfk_2` FOREIGN KEY (`attrNameId`) REFERENCES `mac_attrname` (`attrNameId`),
  CONSTRAINT `mac_goods_attr_conn_ibfk_3` FOREIGN KEY (`attrValueId`) REFERENCES `mac_attrvalue` (`attrValueId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `mac_goods_attr_conn` */

insert  into `mac_goods_attr_conn`(`goodsAttrConnId`,`goodsId`,`attrNameId`,`attrValueId`) values (1,1001,1,1),(2,1001,1,2),(3,1001,2,13),(4,1001,2,14),(5,1001,2,15),(6,1001,2,16),(7,1001,2,17),(8,1001,2,18);

/*Table structure for table `mac_goodstype` */

DROP TABLE IF EXISTS `mac_goodstype`;

CREATE TABLE `mac_goodstype` (
  `typeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类别编号',
  `typePId` int(11) NOT NULL COMMENT '父类别编号',
  `typeName` varchar(50) NOT NULL COMMENT '类别名称',
  `sortKey` int(5) NOT NULL COMMENT '排序号',
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `mac_goodstype` */

insert  into `mac_goodstype`(`typeId`,`typePId`,`typeName`,`sortKey`) values (1,0,'奶粉辅食',0),(2,0,'儿童用品',0),(3,0,'儿童早教',0),(4,0,'儿童服饰',0),(5,0,'孕妈专区',0),(6,1,'进口奶粉',10),(7,1,'宝宝辅食',20),(8,1,'营养品',30),(9,2,'纸尿裤',10),(10,2,'婴儿湿巾',20),(11,2,'婴儿车',30),(12,2,'婴儿床',40),(13,2,'儿童安全座椅',50),(14,3,'儿童玩具',10),(15,3,'早教书籍',20),(16,3,'孕产育儿书',30),(17,4,'童装',10),(18,4,'童鞋',20),(19,4,'宝宝配饰',30),(20,5,'孕妇装',10),(21,5,'孕妇护肤',20),(22,5,'孕妇用品',30);

/*Table structure for table `mac_goodstype_attr` */

DROP TABLE IF EXISTS `mac_goodstype_attr`;

CREATE TABLE `mac_goodstype_attr` (
  `typeAttrId` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类别属性关系编号',
  `typeId` int(11) NOT NULL COMMENT '类别ID（外键）',
  `attrNameId` int(11) NOT NULL COMMENT '属性名ID（外键）',
  PRIMARY KEY (`typeAttrId`),
  KEY `typeId` (`typeId`),
  KEY `attrNameId` (`attrNameId`),
  CONSTRAINT `mac_goodstype_attr_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `mac_goodstype` (`typeId`),
  CONSTRAINT `mac_goodstype_attr_ibfk_2` FOREIGN KEY (`attrNameId`) REFERENCES `mac_attrname` (`attrNameId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `mac_goodstype_attr` */

insert  into `mac_goodstype_attr`(`typeAttrId`,`typeId`,`attrNameId`) values (1,11,1),(2,12,1),(3,13,1),(4,17,1),(5,17,2),(6,18,1),(7,18,4),(8,19,1),(9,20,1),(10,20,2),(11,22,1);

/*Table structure for table `mac_oders_titleblock` */

DROP TABLE IF EXISTS `mac_oders_titleblock`;

CREATE TABLE `mac_oders_titleblock` (
  `orderDetailsId` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单明细编号',
  `ordersId` int(11) NOT NULL COMMENT '订单ID（外键）',
  `skuId` int(11) NOT NULL COMMENT '库存ID（外键）',
  `number` int(11) NOT NULL COMMENT '数量',
  PRIMARY KEY (`orderDetailsId`),
  KEY `ordersId` (`ordersId`),
  KEY `skuId` (`skuId`),
  CONSTRAINT `mac_oders_titleblock_ibfk_1` FOREIGN KEY (`ordersId`) REFERENCES `mac_orders` (`ordersId`),
  CONSTRAINT `mac_oders_titleblock_ibfk_2` FOREIGN KEY (`skuId`) REFERENCES `mac_sku` (`skuId`)
) ENGINE=InnoDB AUTO_INCREMENT=1100 DEFAULT CHARSET=utf8;

/*Data for the table `mac_oders_titleblock` */

insert  into `mac_oders_titleblock`(`orderDetailsId`,`ordersId`,`skuId`,`number`) values (1094,79,1002,3),(1095,79,1003,4),(1096,79,1004,28),(1097,81,1004,5),(1098,82,1004,1),(1099,84,1004,1);

/*Table structure for table `mac_orders` */

DROP TABLE IF EXISTS `mac_orders`;

CREATE TABLE `mac_orders` (
  `ordersId` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `userId` int(11) NOT NULL COMMENT '用户ID（外键）',
  `totalPrice` double DEFAULT NULL COMMENT '应付金额',
  `submitTime` date DEFAULT NULL COMMENT '订单提交时间',
  `payTime` date DEFAULT NULL COMMENT '付款时间',
  `state` varchar(50) NOT NULL COMMENT '订单状态(0购物车，1未提交，2已付款，-1已失效)',
  `recipientId` int(11) DEFAULT NULL COMMENT '收货信息ID（外键）',
  PRIMARY KEY (`ordersId`),
  KEY `userId` (`userId`),
  KEY `recipientId` (`recipientId`),
  CONSTRAINT `mac_orders_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `mac_user` (`userId`),
  CONSTRAINT `mac_orders_ibfk_3` FOREIGN KEY (`recipientId`) REFERENCES `mac_recipientinfo` (`recipientId`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;

/*Data for the table `mac_orders` */

insert  into `mac_orders`(`ordersId`,`userId`,`totalPrice`,`submitTime`,`payTime`,`state`,`recipientId`) values (79,1002,NULL,NULL,NULL,'0',NULL),(80,1002,0,'2019-01-13',NULL,'-1',NULL),(81,1002,0,'2019-01-14',NULL,'-1',NULL),(82,1002,0,'2019-01-14',NULL,'-1',NULL),(83,1002,0,'2019-01-14',NULL,'-1',NULL),(84,1002,0,'2019-01-14',NULL,'-1',NULL),(85,1002,0,'2019-01-14',NULL,'1',NULL);

/*Table structure for table `mac_recipientinfo` */

DROP TABLE IF EXISTS `mac_recipientinfo`;

CREATE TABLE `mac_recipientinfo` (
  `recipientId` int(11) NOT NULL AUTO_INCREMENT COMMENT '收货信息编号',
  `userId` int(11) NOT NULL COMMENT '用户编号（外键）',
  `recipientAddress` varchar(255) NOT NULL COMMENT '收货地址',
  `recipientOfficer` varchar(50) NOT NULL COMMENT '收货人',
  `phone` varchar(50) NOT NULL COMMENT '手机号',
  `isDefault` int(5) NOT NULL COMMENT '是否默认（0，1是默认）',
  PRIMARY KEY (`recipientId`),
  KEY `userId` (`userId`),
  CONSTRAINT `mac_recipientinfo_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `mac_user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mac_recipientinfo` */

insert  into `mac_recipientinfo`(`recipientId`,`userId`,`recipientAddress`,`recipientOfficer`,`phone`,`isDefault`) values (1,1002,'江西省南昌市进贤县','张碧风','18679178671',1);

/*Table structure for table `mac_sku` */

DROP TABLE IF EXISTS `mac_sku`;

CREATE TABLE `mac_sku` (
  `skuId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'sku编号',
  `goodsId` int(11) NOT NULL COMMENT '商品编号（外键）',
  `skuAttrs` varchar(50) NOT NULL COMMENT 'sku属性,比如：[1:1,2:3]相当于([颜色:白色,尺寸:XL])',
  `price` double NOT NULL COMMENT '价格',
  `store` int(11) NOT NULL COMMENT '库存',
  `skuSales` int(11) DEFAULT NULL COMMENT 'sku销量',
  `commentNum` int(11) DEFAULT NULL COMMENT '评论数',
  `photoUrl` varchar(255) DEFAULT NULL COMMENT '商品图片默认资源路径',
  PRIMARY KEY (`skuId`),
  KEY `goodsId` (`goodsId`),
  CONSTRAINT `mac_sku_ibfk_1` FOREIGN KEY (`goodsId`) REFERENCES `mac_goods` (`goodsId`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8;

/*Data for the table `mac_sku` */

insert  into `mac_sku`(`skuId`,`goodsId`,`skuAttrs`,`price`,`store`,`skuSales`,`commentNum`,`photoUrl`) values (1001,1001,'1:1,2:13',150,0,100,NULL,'res/static/img/hot2.png'),(1002,1001,'1:1,2:14',150,0,100,NULL,'res/static/img/hot2.png'),(1003,1001,'1:2,2:13',150,20,100,NULL,'res/static/img/hot3.png'),(1004,1001,'1:2,2:14',150,20,100,NULL,'res/static/img/hot3.png'),(1005,1001,'1:2,2:15',150,0,100,NULL,'res/static/img/hot3.png'),(1006,1001,'1:2,2:16',150,0,100,NULL,'res/static/img/hot3.png');

/*Table structure for table `mac_user` */

DROP TABLE IF EXISTS `mac_user`;

CREATE TABLE `mac_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `userName` varchar(50) NOT NULL COMMENT '账户名称',
  `userPwd` varchar(50) NOT NULL COMMENT '账户密码',
  `phoneNumber` varchar(50) NOT NULL COMMENT '手机号',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8;

/*Data for the table `mac_user` */

insert  into `mac_user`(`userId`,`userName`,`userPwd`,`phoneNumber`) values (1001,'admin','admin123','18679178671'),(1002,'zhangsan','zhangsan','18579108671');

/*Table structure for table `mac_user_goods` */

DROP TABLE IF EXISTS `mac_user_goods`;

CREATE TABLE `mac_user_goods` (
  `collectionId` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏编号',
  `userId` int(11) NOT NULL COMMENT '用户ID（外键）',
  `goodsId` int(11) NOT NULL COMMENT '商品ID（外键）',
  PRIMARY KEY (`collectionId`),
  KEY `userId` (`userId`),
  KEY `goodsId` (`goodsId`),
  CONSTRAINT `mac_user_goods_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `mac_user` (`userId`),
  CONSTRAINT `mac_user_goods_ibfk_2` FOREIGN KEY (`goodsId`) REFERENCES `mac_goods` (`goodsId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `mac_user_goods` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
