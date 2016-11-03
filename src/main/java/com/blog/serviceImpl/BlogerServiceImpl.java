package com.blog.serviceImpl;

import com.blog.entity.Bloger;
import com.blog.mapper.BlogerMapper;
import com.blog.service.BlogerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by ldb on 2016/9/22.
 */
@Service("blogerService")
public class BlogerServiceImpl implements BlogerService {

    @Resource
    private BlogerMapper blogerMapper;

    @Override
    public Bloger getBloger() {
        return blogerMapper.getBloger();
    }

	@Override
	public Bloger login(Map<String, Object> map) {
		return blogerMapper.login(map);
	}

	@Override
	public int updatePassword(Map<String, Object> map) {
		return blogerMapper.updatePassword(map);
	}

	@Override
	public int updateBlogerInfo(Bloger bloger) {
		return blogerMapper.updateBlogerInfo(bloger);
	}

	@Override
	public int updateWebClick() {
		return blogerMapper.updateWebClick();
	}

	@Override
	public Bloger getByUserName(String userName) {
		return blogerMapper.getByUserName(userName);
	}
}
