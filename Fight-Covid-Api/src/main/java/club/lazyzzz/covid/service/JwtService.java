package club.lazyzzz.covid.service;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtService {

    /**
     * 生成token
     *
     * @param userId 用户id
     * @return token字符串
     */
    String generator(Long userId);

    /**
     * 验证token
     *
     * @param token token字符串
     * @return 解析后的token
     */
    DecodedJWT verify(String token);
}
