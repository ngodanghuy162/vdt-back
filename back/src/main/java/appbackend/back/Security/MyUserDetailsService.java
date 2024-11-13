package appbackend.back.Security;



import appbackend.back.model.Account;
import appbackend.back.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {


    private final AccountRepository accRepository;

    @Autowired
    public MyUserDetailsService(AccountRepository accRepository) {
        this.accRepository = accRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account acc = accRepository.findByUsername(username);
        if(acc != null) {
            return new UserPrincipal(acc);
        }  else {
            throw new UsernameNotFoundException("Cannot found username: " + username);
        }
    }

    public Object getUserInfoByUsername(String username) throws UsernameNotFoundException {
        Account acc = accRepository.findByUsername(username);
        if(acc != null) {
            return acc;
        } else {
            throw new UsernameNotFoundException("Cannot found username: " + username);
        }
    }
}
