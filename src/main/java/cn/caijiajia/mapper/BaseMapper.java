package cn.caijiajia.mapper;

import java.util.List;

import cn.caijiajia.domain.Time;

public interface BaseMapper {
	List<Time> selectBase(int id);
}
