package com.example.blog.service.serviceImpl;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import com.example.blog.common.Constant;
import com.example.blog.service.TokenService;
import com.example.blog.util.RedisUtills;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisUtills redisUtills;

    /**
     * 创建token
     *
     * @return
     */
    @Override
    public String createToken() {
        String str = UUID.randomUUID().toString().replace("-", "");
        StrBuilder token = new StrBuilder();
        try {
            token.append(str);
            redisUtills.setEx(token.toString(), token.toString(), 10000L);
            boolean notEmpty = StrUtil.isNotEmpty(token.toString());
            if (notEmpty) {
                return token.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 检验token
     *
     * @param request
     * @return
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {

        String token = request.getHeader(Constant.TOKEN_NAME);
        if (StrUtil.isBlank(token)) {// header中不存在token
            token = request.getParameter(Constant.TOKEN_NAME);
            if (StrUtil.isBlank(token)) {// parameter中也不存在token
                log.info("不存在参数token");
                return false;
            }
        }

        if (!redisUtills.exists(token)) {
            log.info("redis中没有此token");
            return false;
        }

        boolean remove = redisUtills.remove(token);
        if (!remove) {
            log.info("移除不成功");
            return false;
        }
        return true;
    }
}
