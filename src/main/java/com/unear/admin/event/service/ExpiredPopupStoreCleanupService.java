package com.unear.admin.event.service;


public interface ExpiredPopupStoreCleanupService {

    /**
     * 이벤트가 종료된 팝업스토어를 DB에서 완전히 삭제한다.
     */
    void cleanUpExpiredPopupStores();
}