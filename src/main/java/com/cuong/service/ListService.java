package com.cuong.service;

import com.cuong.models.List;

public interface ListService extends BaseService<Long, List> {

	void deleteListAndRelatedWords(Long id);

}
