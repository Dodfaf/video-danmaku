package com.videodanmaku.auth.domain.convert;

import com.videodanmaku.auth.domain.entity.AuthUserBO;
import com.videodanmaku.auth.infra.basic.entity.AuthUser;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-31T19:46:44+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class AuthUserBOConverterImpl implements AuthUserBOConverter {

    @Override
    public AuthUser convertBOToEntity(AuthUserBO authUserBO) {
        if ( authUserBO == null ) {
            return null;
        }

        AuthUser authUser = new AuthUser();

        authUser.setId( authUserBO.getId() );
        authUser.setUserName( authUserBO.getUserName() );
        authUser.setStatus( authUserBO.getStatus() );
        authUser.setAvatar( authUserBO.getAvatar() );
        authUser.setNickName( authUserBO.getNickName() );
        authUser.setEmail( authUserBO.getEmail() );
        authUser.setPhone( authUserBO.getPhone() );
        authUser.setPassword( authUserBO.getPassword() );
        authUser.setSex( authUserBO.getSex() );
        authUser.setIntroduce( authUserBO.getIntroduce() );
        authUser.setIsDeleted( authUserBO.getIsDeleted() );

        return authUser;
    }

    @Override
    public AuthUserBO convertEntityToBO(AuthUser authUser) {
        if ( authUser == null ) {
            return null;
        }

        AuthUserBO authUserBO = new AuthUserBO();

        authUserBO.setId( authUser.getId() );
        authUserBO.setUserName( authUser.getUserName() );
        authUserBO.setStatus( authUser.getStatus() );
        authUserBO.setAvatar( authUser.getAvatar() );
        authUserBO.setNickName( authUser.getNickName() );
        authUserBO.setEmail( authUser.getEmail() );
        authUserBO.setPhone( authUser.getPhone() );
        authUserBO.setPassword( authUser.getPassword() );
        authUserBO.setSex( authUser.getSex() );
        authUserBO.setIntroduce( authUser.getIntroduce() );
        authUserBO.setIsDeleted( authUser.getIsDeleted() );

        return authUserBO;
    }

    @Override
    public List<AuthUserBO> convertEntityToBO(List<AuthUser> authUserList) {
        if ( authUserList == null ) {
            return null;
        }

        List<AuthUserBO> list = new ArrayList<AuthUserBO>( authUserList.size() );
        for ( AuthUser authUser : authUserList ) {
            list.add( convertEntityToBO( authUser ) );
        }

        return list;
    }
}
