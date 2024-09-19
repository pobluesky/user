package com.pobluesky.user.service;

import com.pobluesky.user.dto.request.ManagerCreateRequestDTO;
import com.pobluesky.user.dto.request.ManagerUpdateRequestDTO;
import com.pobluesky.user.dto.response.ManagerResponseDTO;
import com.pobluesky.user.dto.response.ManagerSummaryResponseDTO;
import com.pobluesky.user.entity.Manager;

import com.pobluesky.global.error.CommonException;
import com.pobluesky.global.error.ErrorCode;
import com.pobluesky.user.repository.ManagerRepository;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerService {
    
    private final SignService signService;

    private final ManagerRepository managerRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ManagerResponseDTO signUp(ManagerCreateRequestDTO signUpDto) {
        if (managerRepository.existsByEmail(signUpDto.email())) {
            throw new CommonException(ErrorCode.ALREADY_EXISTS_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(signUpDto.password());

        Manager manager = signUpDto.toManagerEntity(encodedPassword, "USER");

        return ManagerResponseDTO.from(managerRepository.save(manager));
    }

    @Transactional(readOnly = true)
    public List<ManagerResponseDTO> getManagers() {
        List<Manager> managers = managerRepository.findAll();

        return managers.stream()
            .map(ManagerResponseDTO::from)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ManagerResponseDTO getManagerById(String token, Long targetId) {
        Long userId = signService.parseToken(token);

        if (!userId.equals(targetId))
            throw new CommonException(ErrorCode.USER_NOT_MATCHED);

        Manager manager = managerRepository.findById(userId)
            .orElseThrow(() -> new CommonException(ErrorCode.USER_NOT_FOUND));

        return  ManagerResponseDTO.from(manager);
    }

    @Transactional(readOnly = true)
    public ManagerSummaryResponseDTO getSummaryManagerById(Long targetId) {

        Manager manager = managerRepository.findById(targetId)
            .orElseThrow(() -> new CommonException(ErrorCode.USER_NOT_FOUND));

        return ManagerSummaryResponseDTO.from(manager);
    }

    @Transactional
    public ManagerResponseDTO updateManagerById(
        String token,
        Long targetId,
        ManagerUpdateRequestDTO managerUpdateRequestDTO
    ) {
        Manager manager = validateUser(token, targetId);
        
        manager.updateManager(
            managerUpdateRequestDTO.name(),
            managerUpdateRequestDTO.email(),
            managerUpdateRequestDTO.password(),
            managerUpdateRequestDTO.phone()
        );

        return ManagerResponseDTO.from(manager);
    }

    @Transactional
    public void deleteManagerById(String token, Long targetId) {
        Manager manager = validateUser(token, targetId);

        manager.deleteUser();
    }

    private Manager validateUser(String token, Long targetId) {
        Long userId = signService.parseToken(token);

        Manager manager = managerRepository.findById(userId)
            .orElseThrow(() -> new CommonException(ErrorCode.USER_NOT_FOUND));

        if (!userId.equals(targetId))
            throw new CommonException(ErrorCode.USER_NOT_MATCHED);
        return manager;
    }

    public boolean existsById(Long userId) {
        return managerRepository.existsById(userId);
    }

    public ManagerResponseDTO getManagerByIdWithoutToken(Long userId) {

        return managerRepository.findById(userId)
            .map(ManagerResponseDTO::from)
            .orElseThrow(() -> new CommonException(ErrorCode.USER_NOT_FOUND));
    }
}
