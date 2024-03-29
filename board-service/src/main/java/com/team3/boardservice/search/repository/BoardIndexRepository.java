//package com.team3.boardservice.search.repository;
//
//import com.team3.boardservice.search.entity.BoardIndex;
//import com.team3.boardservice.search.entity.SearchItem;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
//public interface BoardIndexRepository extends ElasticsearchRepository<BoardIndex, String> {
//    List<BoardIndex> findByBoardContentContaining(String query);
//}
