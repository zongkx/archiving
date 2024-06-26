//package com.demo.config;
//
//import static com.alibaba.fastjson.serializer.SerializerFeature.DisableCircularReferenceDetect;
//import static com.alibaba.fastjson.serializer.SerializerFeature.QuoteFieldNames;
//import static com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue;
//import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty;
//import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullNumberAsZero;
//import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//
//
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer  {
//	@Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        //序列化配置
//        FastJsonConfig config = new FastJsonConfig();
//        config.setSerializerFeatures(
//                QuoteFieldNames,// 输出key时是否使用双引号
//                WriteMapNullValue,// 是否输出值为null的字段
//                WriteNullNumberAsZero,//数值字段如果为null,输出为0,而非null
//                WriteNullListAsEmpty,//List字段如果为null,输出为[],而非null
//                WriteNullStringAsEmpty,//字符类型字段如果为null,输出为"",而非null
//                //WriteNullBooleanAsFalse,//Boolean字段如果为null,输出为false,而非null
//                //WriteNullStringAsEmpty,// null String不输出
//                //WriteMapNullValue,//null String也要输出
//                //WriteDateUseDateFormat,//Date的日期转换器
//                DisableCircularReferenceDetect//禁止循环引用
//        );
//        List<MediaType> fastMediaTypes = new ArrayList<>();
//        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        converter.setSupportedMediaTypes(fastMediaTypes);//解决返回JSON中文乱码问题
//        
//        converter.setFastJsonConfig(config);
//        converters.add(converter);
//    }
//	/*
//	 * @Bean public HttpMessageConverter<String> responseBodyConverter(){
//	 * StringHttpMessageConverter converter = new
//	 * StringHttpMessageConverter(Charset.forName("UTF-8")); return converter; }
//	 */
//
//}