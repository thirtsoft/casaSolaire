package com.casaSolaire.repository;

import com.casaSolaire.dto.UtilisateurDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Rollback(false)
    public void testCreateUtilisateur() {
        String username = "thir";
        String mobile = "779440310";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );

        assertNotNull(utilisateurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateUtilisateur() {
        String username = "thir";
        String mobile = "779440310";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );

        String usernameUser = "Note1111";
        utilisateurDto.setUsername(usernameUser);
        utilisateurDto.setId((long) 1);
        UtilisateurDto.fromEntityToDto(utilisateurRepository.save(UtilisateurDto.fromDtoToEntity(utilisateurDto)));

        assertThat(utilisateurDto.getUsername()).isEqualTo(usernameUser);

    }

    @Test
    public void testFindById() {
        String username = "thir";
        String mobile = "779440310";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );

        boolean isExistUser = utilisateurRepository.findById(utilisateurDtoResult.getId()).isPresent();

        assertTrue(isExistUser);

    }

    @Test
    public void testFindAll() {
        String username = "thir";
        String mobile = "779440310";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );

        String usernameUser = "thir";
        String mobileUser = "779440310";
        UtilisateurDto utilisateurDto1 = new UtilisateurDto();
        utilisateurDto1.setUsername(usernameUser);
        utilisateurDto1.setMobile(mobileUser);

        UtilisateurDto utilisateurDtoResult1 = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto1)
                )
        );

        List<?> users = utilisateurRepository.findAll();

        assertThat(users).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String username = "thir";
        String mobile = "779440310";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );

        boolean isExistBeforeDelete = utilisateurRepository.findById(utilisateurDtoResult.getId()).isPresent();

        utilisateurRepository.deleteById(utilisateurDtoResult.getId());

        boolean notExistAfterDelete = utilisateurRepository.findById(utilisateurDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }
}
