package com.example.chap07;

public class UserRegister {
  private WeakPasswordChecker passwordChecker;
  private UserRepository userRepository;
  private EmailNotifier emailNotifier;

  public UserRegister(WeakPasswordChecker passwordChecker, UserRepository userRepository, EmailNotifier emailNotifier) {
    this.passwordChecker = passwordChecker;
    this.userRepository = userRepository;
    this.emailNotifier = emailNotifier;
  }

  public void register(String id, String pw, String email) {
    if (passwordChecker.checkPasswordWeak(pw)) {
      throw new WeakPasswordException();
    }

    User user = userRepository.findById(id);
    if (user != null) {
      throw new DupIdException();
    }

    userRepository.save(new User(email, id, pw));

    emailNotifier.sendRegisterEmail(email);
  }
}
