package com.cbh.web;

import com.cbh.mongo.FBMessage;
import com.cbh.mongo.Statements;
import com.cbh.service.FBMessageMongoService;
import com.cbh.service.StatementsMongoService;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Created by Tommy on 2017/4/14.
 */
@RequestMapping("/chatfuel")
@Controller
public class ChatFuelController {

    @Autowired
    private StatementsMongoService statementsMongoService;

    @Autowired
    private FBMessageMongoService fbMessageMongoService;

    @RequestMapping(value = "block",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String blockTemplate(@RequestParam(value = "test") String test, HttpServletRequest httpServletRequest) {
        return "{\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"attachment\": {\n" +
                "        \"type\": \"template\",\n" +
                "        \"payload\": {\n" +
                "          \"template_type\": \"button\",\n" +
                "          \"text\": \"Hello!\",\n" +
                "          \"buttons\": [\n" +
                "            {\n" +
                "              \"type\": \"show_block\",\n" +
                "              \"block_name\": \"some block name\",\n" +
                "              \"title\": \"Show the block!\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"type\": \"web_url\",\n" +
                "              \"url\": \"https://pixabay.com/zh/%E5%AE%B6%E8%9D%87-%E9%A3%9E-%E5%8A%A8%E7%89%A9-%E7%BB%BF%E8%89%B2-%E7%89%B9%E5%86%99-%E6%98%86%E8%99%AB-2221549/\",\n" +
                "              \"title\": \"Buy Item\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @RequestMapping(value = "gallery",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String gallery(HttpServletRequest httpServletRequest) {
        return "{\n" +
                " \"messages\": [\n" +
                "    {\n" +
                "      \"attachment\":{\n" +
                "        \"type\":\"template\",\n" +
                "        \"payload\":{\n" +
                "          \"template_type\":\"generic\",\n" +
                "          \"elements\":[\n" +
                "            {\n" +
                "              \"title\":\"Classic White T-Shirt\",\n" +
                "              \"image_url\":\"https://cdn.pixabay.com/photo/2017/04/11/21/34/giraffe-2222908_960_720.jpg\",\n" +
                "              \"subtitle\":\"Soft white cotton t-shirt is back in style\",\n" +
                "              \"buttons\":[\n" +
                "                {\n" +
                "                  \"type\":\"web_url\",\n" +
                "                  \"url\":\"https://pixabay.com/zh/%E5%AE%B6%E8%9D%87-%E9%A3%9E-%E5%8A%A8%E7%89%A9-%E7%BB%BF%E8%89%B2-%E7%89%B9%E5%86%99-%E6%98%86%E8%99%AB-2221549/\",\n" +
                "                  \"title\":\"View Item\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"type\":\"web_url\",\n" +
                "                  \"url\":\"https://pixabay.com/zh/%E5%AE%B6%E8%9D%87-%E9%A3%9E-%E5%8A%A8%E7%89%A9-%E7%BB%BF%E8%89%B2-%E7%89%B9%E5%86%99-%E6%98%86%E8%99%AB-2221549/\",\n" +
                "                  \"title\":\"Buy Item\"\n" +
                "                }\n" +
                "              ]\n" +
                "            },\n" +
                "            {\n" +
                "              \"title\":\"Classic Grey T-Shirt\",\n" +
                "              \"image_url\":\"https://cdn.pixabay.com/photo/2017/04/11/21/34/giraffe-2222908_960_720.jpg\",\n" +
                "              \"subtitle\":\"Soft gray cotton t-shirt is back in style\",\n" +
                "              \"buttons\":[\n" +
                "                {\n" +
                "                  \"type\":\"web_url\",\n" +
                "                  \"url\":\"https://pixabay.com/zh/%E5%AE%B6%E8%9D%87-%E9%A3%9E-%E5%8A%A8%E7%89%A9-%E7%94%9F%E6%B4%BB-%E6%98%86%E8%99%AB-%E7%89%B9%E5%86%99-2222158/\",\n" +
                "                  \"title\":\"View Item\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"type\":\"web_url\",\n" +
                "                  \"url\":\"https://pixabay.com/zh/%E5%AE%B6%E8%9D%87-%E9%A3%9E-%E5%8A%A8%E7%89%A9-%E7%BB%BF%E8%89%B2-%E7%89%B9%E5%86%99-%E6%98%86%E8%99%AB-2221549/\",\n" +
                "                  \"title\":\"Buy Item\"\n" +
                "                }\n" +
                "              ]\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    @RequestMapping(value = "chat",method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String gallery(HttpServletRequest httpServletRequest,@RequestParam(value = "ask",required = false)String ask) {
        String responseAns= "對不起,我聽不懂你再說什麼";
        String responseAnsTwo= "對不起,我聽不懂你再說什麼";
        String askForJeiba="";
        try {
            ask=new String(ask.getBytes(),"utf8");
            System.out.println("ask : "+ ask);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JiebaSegmenter segmenter = new JiebaSegmenter();
        System.out.println(segmenter.process(ask, JiebaSegmenter.SegMode.SEARCH).toString());
        List<SegToken> segTokenList=segmenter.process(ask, JiebaSegmenter.SegMode.SEARCH);
        for(SegToken segToken:segTokenList){
            System.out.println(segToken.word.toString());
            askForJeiba=askForJeiba+segToken.word.toString()+".*";
        }

        FBMessage fbMessage=new FBMessage();
        fbMessage.setCreateDate(new Date());
        fbMessage.setAskMessage(ask);
        fbMessage.setResponseMessage(responseAns);
        fbMessage.getMessagerId();
        fbMessageMongoService.save(fbMessage);

        try {
            List<Statements> statementsList=statementsMongoService.findStatementsByRegexpResponse(askForJeiba);
            if(! (statementsList==null)){
                int ran= (int)(Math.random()*42+1);
                int i=ran % statementsList.size();
                responseAns=statementsList.get(i).getText();
                responseAnsTwo=statementsList.get(0).getText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            return "{\n" +
                    " \"messages\": [\n" +
                    "   {\"text\": \""+new String(responseAns.getBytes(),"iso-8859-1")+"!\"},\n" +
                    "   {\"text\": \""+new String(responseAnsTwo.getBytes(),"iso-8859-1")+"\"}\n" +
                    " ]\n" +
                    "}";
        } catch (UnsupportedEncodingException e) {
            return "{\n" +
                    " \"messages\": [\n" +
                    "   {\"text\": \"不好意思目前無法聽懂你的話!\"},\n" +
                    "   {\"text\": \"不好意思目前無法聽懂你的話!\"}\n" +
                    " ]\n" +
                    "}";
        }
    }
}
