package com.example.chap02.password;

public class PasswordStrengthMeter {

  public PasswordStrength meter(String s) {
    if (s == null || s.trim().isEmpty())
      return PasswordStrength.INVALID;

    int metCounts = getMetCriteriaCounts(s);

    if (metCounts <= 1) return PasswordStrength.WEAK;
    if (metCounts == 2) return PasswordStrength.NORMAL;

    return PasswordStrength.STRONG;
  }

  private boolean meetsContainingNumberCriteria(String s) {
    for (char ch : s.toCharArray()) {
      if (ch >= '0' && ch <= '9') {
        return true;
      }
    }
    return false;
  }

  private boolean meetsContainingUppercaseCriteria(String s) {
    for (char ch : s.toCharArray()) {
      if (ch >= 'A' && ch <= 'Z') {
        return true;
      }
    }
    return false;
  }

  private int getMetCriteriaCounts(String s) {
    int metCounts = 0;

    if (s.length() >= 8) metCounts++;
    if (meetsContainingNumberCriteria(s)) metCounts++;
    if(meetsContainingUppercaseCriteria(s)) metCounts ++;

    return metCounts;
  }
}
