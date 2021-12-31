package com.furntrade.furntrademanagmentservet.Service;

import com.furntrade.furntrademanagmentservet.Dtos.AccountDto;
import com.furntrade.furntrademanagmentservet.Dtos.EmployeeInfoDto;
import com.furntrade.furntrademanagmentservet.Dtos.UserChangePasswordDto;
import com.furntrade.furntrademanagmentservet.ModelAssemblers.AppUserModelAssembler;
import com.furntrade.furntrademanagmentservet.Models.AppRole;
import com.furntrade.furntrademanagmentservet.Models.AppUser;
import com.furntrade.furntrademanagmentservet.Repositories.AppRoleRepository;
import com.furntrade.furntrademanagmentservet.Repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements IUserService, UserDetailsService {

    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private final AppUserModelAssembler assembler;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        if(user==null){
            log.info("Nisam nasao korisnika");
            throw new UsernameNotFoundException("User is not found in database");
        }
        else {
            log.info("nasao korisnika",username);
        }
        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
        user.getRoles().forEach(role->{authorities.add(new SimpleGrantedAuthority(role.getName()));});

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    @Override
    public AccountDto saveUser(AccountDto newUser) throws ParseException {
        log.info("cuvam podatke");
        var usernameAlreadyExist=userRepository.findByUsername(newUser.getUsername());
        if(usernameAlreadyExist== null)
        {
            AppRole role=roleRepository.findByName("employee");
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            newUser.getRoles().add(role);
            userRepository.save(assembler.convertToAppUserEntity(newUser));
            return newUser;
        }
        else return null;
    }
    @Override
    public void deleteUser(String username){
        log.info("brisem podatke");
        AppUser user=userRepository.findByUsername(username);
        userRepository.deleteById(user.getId());
        log.info("obrisala sam korisnika");
    }
    @Override
    public boolean changePassword(String username, UserChangePasswordDto passwordDto){
        AppUser user=userRepository.findByUsername(username);
        var encodedOldPass=passwordEncoder.encode(passwordDto.getOldPassword());
        var isPasswordCorrect=passwordEncoder.matches(passwordDto.getOldPassword(),user.getPassword());
        if(isPasswordCorrect)
        {
            log.info("korisnik je uneo tacnu sifru");
            user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public EmployeeInfoDto updateProfile(String username, EmployeeInfoDto userDto){
        AppUser user=userRepository.findByUsername(username);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPlaceOfWork(userDto.getPlaceOfWork());
        user.setEmail(userDto.getEmail());
        return assembler.toModel(userRepository.save(user));
    }
    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user=userRepository.findByUsername(username);
        AppRole role=roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public EmployeeInfoDto getUser(String username) {
        return assembler.toModel(userRepository.findByUsername(username));
    }
    public AppUser getUserApp(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public List<EmployeeInfoDto> getUsers() {
        List<EmployeeInfoDto> users=userRepository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return  users;
    }

    private EmployeeInfoDto convertToDto(AppUser user) {
        EmployeeInfoDto usersDto = modelMapper.map(user, EmployeeInfoDto.class);
        return usersDto;
    }

    private AppUser convertToAppUserEntity(EmployeeInfoDto usersDto) throws ParseException {
        AppUser user = modelMapper.map(usersDto, AppUser.class);
        if (usersDto.getUsername() != null) {
            AppUser oldUser = userRepository.findByUsername(usersDto.getUsername());
        }
        return user;
    }

}
