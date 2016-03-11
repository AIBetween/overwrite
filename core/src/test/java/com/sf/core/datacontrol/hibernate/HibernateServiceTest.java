package com.sf.core.datacontrol.hibernate;

import com.sf.core.dto.RcvRuntimeLogEntity;
import org.apache.commons.lang.StringUtils;
import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.StringTokenizer;;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * 描述:
 * <p/>
 * Created by 828477[JAX] on 2016/3/11 16:40.
 */
public class HibernateServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(HibernateServiceTest.class);
    private static final String[] op51Parameter = new String[]{

            "opCode",
            "waybillNo",
            "zoneCode",
            "barOprCode",
            "barScanTm",
            "barScanDt",
            "barUploadTypeCode",
            "opAttachInfo",
            "phone",
            "weightQty",
            "otherInfo",
            "barSn"

    };
    private static ApplicationContext applicationContext;
    private static IHibernateBaseDao hibernateService;
    @BeforeClass
    public static void initContext(){

        String[] paths =new String[]{
                "bean-core.xml"
        };
        applicationContext = new ClassPathXmlApplicationContext(paths);
        hibernateService = (IHibernateBaseDao)applicationContext.getBean("hibernateService");
    }

    @Test
    public void testFindByOneProperty() throws Exception {

        List<RcvRuntimeLogEntity> results = hibernateService.findByOneProperty(RcvRuntimeLogEntity.class, "wayBillNo", "932036391525");

        results.parallelStream().forEach(entity -> {

            String beanObjStr = entity.getBeanObj();
            if ("51".equals(entity.getOpCode())) {

                boolean isLegal = test51OpParameter(entity.getId(), cuttingStr(beanObjStr));
                assertThat(isLegal, is(true));
            }
        });

    }


    private Properties cuttingStr(String recordDtoStr) {

        String trimStr = recordDtoStr.replace("BarRecordBaseDto [", "")
                    .replace("]", "");


        StringTokenizer tokenizer = new StringTokenizer(trimStr, ",");
        Properties properties = new Properties();
        while (tokenizer.hasMoreTokens()) {

            String curStr = tokenizer.nextToken().trim();
            int indexOfEqual = curStr.indexOf("=");
            if (indexOfEqual > 0) {

                properties.put(curStr.substring(0, indexOfEqual), curStr.substring(indexOfEqual + 1));
            }else {

                logger.error("cutting str is ERROR, str:{}", curStr);
            }
        }

        return properties;
    }

    public boolean test51OpParameter(long id, Properties properties) {

        boolean isAllLegalData = true;
        for (String parameterStr : op51Parameter) {

            Object value = properties.get(parameterStr);
            if (properties.get(parameterStr).equals("null")) {

                System.out.println(String.format("*******id:%l, %s:null ********",id, parameterStr));
                isAllLegalData = false;
            } else if(!Optional.ofNullable(value).isPresent()){

                logger.error("parameterStr do not appear off!!!!");
            } else {

                logger.info("key:{} = value:{}", parameterStr, properties.get(parameterStr));
            }
        }

        return isAllLegalData;
    }
}