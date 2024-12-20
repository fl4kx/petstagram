package com.petstagram.model.entity;

import com.petstagram.common.constants.AccountStatus;
import com.petstagram.dto.board.CreateBoardRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board extends Time{
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private AccountStatus useyn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Img> imgList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Like> likes = new ArrayList<>(); // 좋아요 목록

    public Board(CreateBoardRequestDto dto, User user, List<Img> imgList) {
        this.content = dto.getContent();
        this.useyn = AccountStatus.USE;
        this.user = user;
        this.imgList = imgList;

    }
    public void updateContent(String content) {
        this.content = content;
    }
}
