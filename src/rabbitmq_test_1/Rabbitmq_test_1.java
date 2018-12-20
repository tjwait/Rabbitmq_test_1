/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitmq_test_1;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


/**
 *
 * @author lilin
 */
public class Rabbitmq_test_1 {

    /**
     * @param args the command line arguments
     */
    
    private final static String QUEUE_NAME = "hello";//这个是消息队列的名字
    
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        ConnectionFactory factory = new ConnectionFactory();
        //factory.setHost("localhost");
        factory.setHost("114.116.20.198");
        factory.setPort(8086);
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel(3);
            
        //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message_unlock = "{\"MSN\" : \"123abe578\" , \"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Unlock\" }";
        String message_unlock_closed = "{\"MSN\" : \"123abe578\" ,\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Unlock_Close\" }";
        String message_locker_state = "{\"MSN\" : \"123abe578\" ,\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Locker_State\" }";
        String message_set_bv = "{\"MSN\" : \"123abe578\" ,\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Basic_Value\" , \"BN\" : \"0003\" }";//设置指定称编号称重板去皮值
        //String message_set_cur = "{\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Curavture_Value\" , \"BN\" : \"0003\", \"Weight\" : \"2000\" , \"Check_Tem\" : \"0\" , \"Save\" : \"1\" }";//设置指定称编号称重板曲率值
        String message_set_cur = "{\"MSN\" : \"123abe578\" ,\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Curavture_Value\" , \"BN\" : \"0003\", \"Weight\" : \"2000\" , \"Save\" : \"1\" }";//设置指定称编号称重板曲率值
        String message_get_board_state = "{\"MSN\" : \"123abe578\" ,\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Board_State\" }";
        String message_get_wei = "{\"MSN\" : \"123abe578\" ,\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Weight_Value\" , \"Data\":[ \"0001\", \"0002\" , \"0003\" ] }";
        String message_shopping = "{\"MSN\" : \"123abe578\" ,\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Shopping\" , \"Data\":[\"0001\", \"0003\"] }";
        //以上命令为新版命令
        
        
        //String message_onshelf = new String("您好呀".getBytes(),"utf8" );
        //String message_onshelf = "{\"devid\":\"shangbaotest1\",\"cmdid\":\"OnShelf\",\"Data\":[[\"item4\",\"01020304\",\"0003\",\"脉动\",\"测试供应商\",\"2\",\"3\",\"2000\",\"3\",\"4000\",\"0\"] ， [\"item4\",\"01020304\",\"0003\",\"脉动\",\"测试供应商\",\"2\",\"3\",\"2000\",\"3\",\"4000\",\"0\"]]}";
        String message_onshelf = "{\"devid\":\"shangbaotest1\",\"cmdid\":\"OnShelf\",\"Data\":[[\"item5\",\"01020304\",\"0003\",\"脉动\",\"测试供应商\",\"2\",\"3\",\"2000\",\"3\",\"4000\",\"0\"] , [\"item6\",\"01020304\",\"0003\",\"脉动\",\"测试供应商\",\"2\",\"3\",\"2000\",\"3\",\"4000\",\"0\"]]}";
        //商品下架只需要商品编号和目标称重板编号即可，但此处为保证消息可读，因此仍旧采用完整的一条商品信息
        //String message_offshelf = "{\"devid\":\"shangbaotest1\",\"cmdid\":\"OffShelf\",\"Data\":[[\"item2\",\"01020304\",\"0001\",\"E土豆\",\"测试供应商\",\"1\",\"0\",\"0\",\"0\",\"4000\",\"8.6\"]]}";
        String message_offshelf = "{\"devid\":\"shangbaotest1\",\"cmdid\":\"OffShelf\",\"Data\":[[\"item1\",\"01020304\",\"0003\",\"E土豆\",\"测试供应商\",\"1\",\"0\",\"0\",\"0\",\"4000\",\"8.6\"]]}";
        String message_openlock_without_weigh = "{\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Unlock\" }";
        //String message_shopping = "{\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Shopping\" }";
        String message_basic_value = "{\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"Basic_Value\" }";
        String messasge_pp = "{\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"P&P\" }";
        //String message_Create_Sheme = "{\"devid\":\"shangbaotest1\",\"cmdid\":\"Create_Sheme\",\"scheme_id\":\"scheme2\",\"scheme_name\":\"ml可乐方案\",\"issave\":\"1\", \"error_value\":\"5\","
          //      + "                     \"Data\":[[\"item1\",\"橘子汁\",\"8\",\"80\",\"1.5\"] , [\"item2\",\"矿泉水\",\"20\",\"120\",\"2\"]]}";
        String message_Create_Sheme = "{\"devid\":\"shangbaotest1\",\"cmdid\":\"Create_Sheme\",\"scheme_id\":\"scheme3\",\"scheme_name\":\"单品测试方案\",\"issave\":\"1\", \"error_value\":\"5\","
              + "                     \"Data\":[[\"item1\",\"橘子汁\",\"3\",\"1000\",\"1.5\"]]}";
        
        String message_sql_select = "{\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"SQL_Select\" , \"SqlStr\" : \"select * from smart_sales_counter.counter_info where name = '保鲜柜1号'\" }";
        String message_sql_delete = "{\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"SQL_Updata\" , \"SqlStr\" : \"delete from smart_sales_counter.saletype where type = '24'\" }";
        String message_sql_insert = "{\"devid\" : \"shangbaotest1\" , \"cmdid\" : \"SQL_Updata\" , \"SqlStr\" : \"INSERT INTO smart_sales_counter.saletype VALUES ('5' , '24' , '你好' , '哈哈' ) \" }";
        
        
        //String message_onshelf = toUTF8("{\"devid\":\"shangbaotest1\",\"cmdid\":\"OnShelf\",\"Data\":[[\"item1\",\"02020304\",\"0003\",\"可乐可乐可乐\",\"可口阿道夫\",\"1\",\"0\",\"0\",\"0\",\"500\",\"1.7\"]]}");
        //message_onshelf = new String(message_onshelf.getBytes(),"UTF8");
        channel.basicPublish("amq.topic", "shangbaotest1", null, message_locker_state.getBytes());
            
        System.out.println(" [x] Sent " /*+ message*/ );
        channel.close();
        connection.close();
        
    }
    
    
    
public static String toUTF8(String str) {
	if (isEmpty(str)) {
		return "";
	}
	try {
		if (str.equals(new String(str.getBytes("GB2312"), "GB2312"))) {
			str = new String(str.getBytes("GB2312"), "utf-8");
			return str;
		}
	} catch (Exception exception) {
	}
	try {
		if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
			str = new String(str.getBytes("ISO-8859-1"), "utf-8");
			return str;
		}
	} catch (Exception exception1) {
	}
	try {
		if (str.equals(new String(str.getBytes("GBK"), "GBK"))) {
			str = new String(str.getBytes("GBK"), "utf-8");
			return str;
		}
	} catch (Exception exception3) {
	}
	return str;
}
 
/**
 * 判断是否为空
 * 
 * @param str
 * @return
 */
public static boolean isEmpty(String str) {
	// 如果字符串不为null，去除空格后值不与空字符串相等的话，证明字符串有实质性的内容
	if (str != null && !str.trim().isEmpty()) {
		return false;// 不为空
	}
	return true;// 为空

    
}

}