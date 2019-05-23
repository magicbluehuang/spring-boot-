package com.example.demospringboot;

import com.example.demospringboot.dao.JMTestMapper;
import com.example.demospringboot.utils.RSAEncryptUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoSpringbootApplicationTests {
    
    @Autowired
    private JMTestMapper jm;

    @Test
    public  void contextLoads()throws  Exception {
        //生成公钥和私钥
        Map<Integer, String> integerStringMap = RSAEncryptUtil.genKeyPair();
        //加密字符串
        String message = "df723820";
        System.out.println("随机生成的公钥为:" + integerStringMap.get(0));
        System.out.println("随机生成的私钥为:" + integerStringMap.get(1));
        String messageEn = RSAEncryptUtil.encrypt(message,integerStringMap.get(0));
        System.out.println(message + "\t加密后的字符串为:" + messageEn);
        String messageDe = RSAEncryptUtil.decrypt(messageEn,integerStringMap.get(1));
        System.out.println("还原后的字符串为:" + messageDe);
        System.out.println("*****************************************************************************");
        String data="验证该数据是否为合法的服务器发送";
        //new String(Base64.encodeBase64(signature))转成字符串
        String signatureStr = RSAEncryptUtil.signature(integerStringMap.get(1), data);
        System.out.println(signatureStr);
        System.out.print(RSAEncryptUtil.verify(integerStringMap.get(0),signatureStr ,data));

    }
}
