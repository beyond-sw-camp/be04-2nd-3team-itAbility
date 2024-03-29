package com.team3.boardservice.search.service;

import co.elastic.clients.elasticsearch.ml.Job;
import com.team3.boardservice.search.entity.BoardIndex;
import com.team3.boardservice.search.entity.CommentIndex;
import com.team3.boardservice.search.entity.RecruitIndex;
import com.team3.boardservice.search.entity.SearchItem;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class SearchItemService {
    private final ElasticsearchOperations elasticsearchOperations;

    public SearchItemService(ElasticsearchOperations elasticsearchOperations) {


        this.elasticsearchOperations = elasticsearchOperations;
    }

    public List<BoardIndex> searchBoard(String boardQuery) {
        Criteria criteria = new Criteria("board_title").contains(boardQuery);
        CriteriaQuery query = new CriteriaQuery(criteria);

        SearchHits<BoardIndex> searchHits = elasticsearchOperations.search(query, BoardIndex.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
    public List<CommentIndex> searchComment(String commentQuery) {
        Criteria criteria = new Criteria("cmt_content").contains(commentQuery);
        CriteriaQuery query = new CriteriaQuery(criteria);

        SearchHits<CommentIndex> searchHits = elasticsearchOperations.search(query, CommentIndex.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
    public List<RecruitIndex> searchRecruit(String recruitQuery) {
        Criteria criteria = new Criteria("recruit_title").contains(recruitQuery);
        CriteriaQuery query = new CriteriaQuery(criteria);

        SearchHits<RecruitIndex> searchHits = elasticsearchOperations.search(query, RecruitIndex.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public List<SearchItem> searchJob(String jobQuery) {
        Criteria criteria = new Criteria("position_name").contains(jobQuery);
        CriteriaQuery query = new CriteriaQuery(criteria);

        SearchHits<SearchItem> searchHits = elasticsearchOperations.search(query, SearchItem.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}

