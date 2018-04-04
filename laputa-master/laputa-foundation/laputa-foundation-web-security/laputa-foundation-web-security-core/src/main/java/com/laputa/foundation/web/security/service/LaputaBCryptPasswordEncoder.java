package com.laputa.foundation.web.security.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by JiangDongPing on 2018/04/04.
 */
@Service("laputaBCryptPasswordEncoder")
public class LaputaBCryptPasswordEncoder extends BCryptPasswordEncoder {
}
