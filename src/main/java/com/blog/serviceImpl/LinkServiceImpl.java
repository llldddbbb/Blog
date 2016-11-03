package com.blog.serviceImpl;

import com.blog.mapper.LinkMapper;
import com.blog.entity.Link;
import com.blog.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ldb on 2016/9/22.
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    public List<Link> findLinkList() {
        return linkMapper.findLinkList();
    }

	@Override
	public int saveLink(Link link) {
		return linkMapper.saveLink(link);
	}

	@Override
	public int updateLink(Link link) {
		return linkMapper.updateLink(link);
	}

	@Override
	public int deleteLink(Integer id) {
		return linkMapper.deleteLink(id);
	}
}
