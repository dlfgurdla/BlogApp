package com.com.blog.view;

public interface InitActivity {
    void init(); //findViewById, new 메모리 관리
    void initLr(); //리스너 등록 순서 중요
    void initSetting(); //각종 기타 세팅
    default void initAdapter(){}
    default void initData(){} //화면 초기화
}
