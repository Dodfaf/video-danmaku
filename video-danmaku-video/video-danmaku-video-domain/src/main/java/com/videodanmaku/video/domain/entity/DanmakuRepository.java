package com.videodanmaku.video.domain.entity;


import com.videodanmaku.video.infra.basic.entity.Danmaku;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DanmakuRepository extends JpaRepository<Danmaku, Integer> {
    List<Danmaku> findByVideoIdAndIsDeleted(Integer videoId, Integer isDeleted);
}