package com.videodanmaku.auth.application.convert;

import com.videodanmaku.auth.application.dto.AuthUserDTO;
import com.videodanmaku.auth.domain.entity.AuthUserBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-03T17:11:08+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_422 (Amazon.com Inc.)"
)
public class AuthUserDTOConverterImpl implements AuthUserDTOConverter {

    @Override
    public AuthUserBO convertDTOToBO(AuthUserDTO authUserDTO) {
        if ( authUserDTO == null ) {
            return null;
        }

        AuthUserBO authUserBO = new AuthUserBO();

        authUserBO.setId( authUserDTO.getId() );
        authUserBO.setUserName( authUserDTO.getUserName() );
        authUserBO.setStatus( authUserDTO.getStatus() );
        authUserBO.setAvatar( authUserDTO.getAvatar() );
        authUserBO.setNickName( authUserDTO.getNickName() );
        authUserBO.setEmail( authUserDTO.getEmail() );
        authUserBO.setPhone( authUserDTO.getPhone() );
        authUserBO.setPassword( authUserDTO.getPassword() );
        authUserBO.setSex( authUserDTO.getSex() );
        authUserBO.setIntroduce( authUserDTO.getIntroduce() );

        return authUserBO;
    }

    @Override
    public AuthUserDTO convertBOToDTO(AuthUserBO authUserBO) {
        if ( authUserBO == null ) {
            return null;
        }

        AuthUserDTO authUserDTO = new AuthUserDTO();

        authUserDTO.setId( authUserBO.getId() );
        authUserDTO.setUserName( authUserBO.getUserName() );
        authUserDTO.setStatus( authUserBO.getStatus() );
        authUserDTO.setAvatar( authUserBO.getAvatar() );
        authUserDTO.setNickName( authUserBO.getNickName() );
        authUserDTO.setEmail( authUserBO.getEmail() );
        authUserDTO.setPhone( authUserBO.getPhone() );
        authUserDTO.setPassword( authUserBO.getPassword() );
        authUserDTO.setSex( authUserBO.getSex() );
        authUserDTO.setIntroduce( authUserBO.getIntroduce() );

        return authUserDTO;
    }

    @Override
    public List<AuthUserDTO> convertBOToDTO(List<AuthUserBO> authUserBO) {
        if ( authUserBO == null ) {
            return null;
        }

        List<AuthUserDTO> list = new ArrayList<AuthUserDTO>( authUserBO.size() );
        for ( AuthUserBO authUserBO1 : authUserBO ) {
            list.add( convertBOToDTO( authUserBO1 ) );
        }

        return list;
    }
}
