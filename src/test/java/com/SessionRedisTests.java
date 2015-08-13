package com;

import org.junit.Test;
import org.springframework.data.redis.RedisConnectionFailureException;

/**
 * Created by dqf on 2015/8/13.
 */
public class SessionRedisTests {
    @Test
    public void sessionExpiry() throws Exception{
//        String port = "8099";
//        try{
//            ConfigurableApplicationContext context = new SpringApplicationBuilder()
//                    .sources(Application.class)
//                    .properties("server.port:8099")
//                    .initializers(new ServerPortInfoApplicationContextInitializer())
//                    .run();
//        }catch (RuntimeException ex){
//            if (!redisServerRunning(ex)){
//                return;
//            }
//        }
//
//        URI uri = URI.create("http://localhost:"+port+"/");
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
//        String uuid1 = response.getBody();
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.set("Cookie",response.getHeaders().getFirst("Set-Cookie"));
//
//        RequestEntity<Void> request = new RequestEntity<Void>(requestHeaders, HttpMethod.GET,uri);
//        String uuid2 = restTemplate.exchange(request, String.class).getBody();
//        assertThat(uuid1,is(equalTo(uuid2)));
//
//        Thread.sleep(5000);
//
//        String uuid3 = restTemplate.exchange(request,String.class).getBody();
//        assertThat(uuid2,is(not(equalTo(uuid3))));
    }

    private boolean redisServerRunning(Throwable ex){
        if (ex instanceof RedisConnectionFailureException){
            return false;
        }
        return ex.getCause() == null || redisServerRunning(ex.getCause());
    }
}


























