package com.videodanmaku.auth.domain.convert;

import com.videodanmaku.auth.domain.entity.AuthUserBO;
import com.videodanmaku.auth.infra.basic.entity.AuthUser;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-04T15:35:54+0800",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.41.0.z20250115-2156, environment: Java 21.0.5 (Eclipse Adoptium)"
)
public class AuthUserBOConverterImpl implements AuthUserBOConverter {

    @Override
    public AuthUser convertBOToEntity(AuthUserBO authUserBO) {
        if ( authUserBO == null ) {
            return null;
        }

        AuthUser authUser = new AuthUser();

        authUser.setAvatar( authUserBO.getAvatar() );
        authUser.setEmail( authUserBO.getEmail() );
        authUser.setId( authUserBO.getId() );
        authUser.setIntroduce( authUserBO.getIntroduce() );
        authUser.setIsDeleted( authUserBO.getIsDeleted() );
        authUser.setNickName( authUserBO.getNickName() );
        authUser.setPassword( authUserBO.getPassword() );
        authUser.setPhone( authUserBO.getPhone() );
        authUser.setSex( authUserBO.getSex() );
        authUser.setStatus( authUserBO.getStatus() );
        authUser.setUserName( authUserBO.getUserName() );

        return authUser;
    }

    @Override
    public AuthUserBO convertEntityToBO(AuthUser authUser) {
        if ( authUser == null ) {
            return null;
        }

        AuthUserBO authUserBO = new AuthUserBO();

        authUserBO.setAvatar( authUser.getAvatar() );
        authUserBO.setEmail( authUser.getEmail() );
        authUserBO.setId( authUser.getId() );
        authUserBO.setIntroduce( authUser.getIntroduce() );
        authUserBO.setIsDeleted( authUser.getIsDeleted() );
        authUserBO.setNickName( authUser.getNickName() );
        authUserBO.setPassword( authUser.getPassword() );
        authUserBO.setPhone( authUser.getPhone() );
        authUserBO.setSex( authUser.getSex() );
        authUserBO.setStatus( authUser.getStatus() );
        authUserBO.setUserName( authUser.getUserName() );

        return authUserBO;
    }
}
