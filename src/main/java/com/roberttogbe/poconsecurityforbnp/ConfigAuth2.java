package com.roberttogbe.poconsecurityforbnp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Author: ROBERT TOGBE - Senior Java Developer and Tech Lead for Industry-Leading Corp.
 * Creation date: 16/12/2020
 * A Show case for BNP - BP2S
 */

@Configuration
public class ConfigAuth2 extends AuthorizationServerConfigurerAdapter {

    private String clientId = "roberttogbe";
    private String clientSecret = "roberttogbe-secret-key";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAxc4Pbbm9D0gdkojbndDrxJYNXRs22z9MLO7FC/HjMfU4BYgW\n" +
            "6EJ8gmeyqLd3CPm+2RC1txXBwiRcyUcxAeyf7aa2lzV+5Hony5ZGM1J0EMqvpsLu\n" +
            "JWrPJbPwhPGxd3m+1aGAcneOpaXBLWRU1nZpCMGzpYLrbLls1O/3uesvvsmfEO4E\n" +
            "NkNycFd75kvtM/95Z/T7wCVK8H6DLgh06rvUB2qFTJBT9XdL4yTkS1PcQl4/LY5A\n" +
            "bG8ZVtMHGutw0isFPvyRv6FFrkxJnOOZu4rRyfiTQsObYHZli99qfTlG9pbKzXKf\n" +
            "RwldbPtzr37bm5ZXDcAHH7d8Qct//CQkHb/wYQIDAQABAoIBAHZdjUv6tpCbdd7G\n" +
            "+KxiZYM4y3xomZXvNbU5mD5Cm9K8S667tgF8L6eVSAq/5oI/eJ3J6wNJCK4VNlTX\n" +
            "vhLJtxXuChtHzswH8SJZpV52u6+EqkGfKgTH+nc0Svwy6SB2gOSzhPp6YqjrHEL+\n" +
            "Y+oF52pvBwJG6nwgJWjyxAnEG8kNLNFS5aJHRD9kcvJTSy6Xg3K1Yv7cgT/cYHVV\n" +
            "AQy1kgXL5auXO2reX61nIxRxPScySg6rJ/a1TdiDv4vDaMHJShozQEFrUT3MvVOW\n" +
            "678LDWDeO2YR/YVgu88AlYfwpQHkYl7bgv33Nn/XPlxS8nRE/AUN6DApwF0GN1Cz\n" +
            "hIlcsDECgYEA+dNp530QLD3z1wteb+5CcQRQHuhk5DmyXsZhMAlaIToUzpCJvnqH\n" +
            "ni+FbjINApA76LuTT/o58WBWeSD9DrMc4/xPa49Y118qnbMroRy9ecKczOwvClSJ\n" +
            "toiVUVWekVHP1+eqq8FB/IiOeNEiXWttwsYOSwxmZ2BQxQ5IWRTkIn0CgYEAyrGF\n" +
            "67EvUJ+5mym0EVLAEWO8iMoZRQxC9x+iydwVY/NcE0noXeOT8/6HyCszMPuEPVv3\n" +
            "l8e4RqoHf36MxR0kap98besDTczMR+K4ICp1c3jYtU4k1vnurGPxmP91E8VQyaj2\n" +
            "Zbxtlfgl1sDSMmEsOfLICDBdoYylqUP3tfu5JrUCgYABjidioYywOdKCzDdSSxFu\n" +
            "0rANATVfllxd6q5ANk6xVkM65ft1jB28Bl+2R+l80pkM4d4Q6WXi8s6XAnWQ2dyc\n" +
            "Lr3v1XygGp+RS8RBxIQJdgSz4eSJE+2njUIgt3+9N90rYu3VE+btQ4xfhC3Klxif\n" +
            "1cvQ+8rHafoWv/gDVfX1oQKBgQCXOduw/YFPpdvU6EhwpEyorMD9TcuMuJk9x1yt\n" +
            "Poa3C9qmiX3UjvbUFBM6RQDsirsfDH6tv1S9ZB40ATcF2bhR1KwBVlYgtauz9nNp\n" +
            "jEQU4xTMMIFL4y0vcWHPxMiU96JO0sEDJpKcUYfpsfMho15b3cdgVLWG0mjG4O7v\n" +
            "199MAQKBgQC7VzvzrwvuiN239cg44KH3IejccsHHdOf+p+JK0bC6iFGyHGDNtNK7\n" +
            "Y+blvMnozFsed8CeDOidNii8ilQK3S0YQk//L/M3cJWGedhjP8L4pRJjKhQ5SnF7\n" +
            "opJ9aDOac7b+zsCp7uNyr0VJ35xpI+oHxE6bBFK8TYdCx4iKzPLc4Q==\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxc4Pbbm9D0gdkojbndDr\n" +
            "xJYNXRs22z9MLO7FC/HjMfU4BYgW6EJ8gmeyqLd3CPm+2RC1txXBwiRcyUcxAeyf\n" +
            "7aa2lzV+5Hony5ZGM1J0EMqvpsLuJWrPJbPwhPGxd3m+1aGAcneOpaXBLWRU1nZp\n" +
            "CMGzpYLrbLls1O/3uesvvsmfEO4ENkNycFd75kvtM/95Z/T7wCVK8H6DLgh06rvU\n" +
            "B2qFTJBT9XdL4yTkS1PcQl4/LY5AbG8ZVtMHGutw0isFPvyRv6FFrkxJnOOZu4rR\n" +
            "yfiTQsObYHZli99qfTlG9pbKzXKfRwldbPtzr37bm5ZXDcAHH7d8Qct//CQkHb/w\n" +
            "YQIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }
}
