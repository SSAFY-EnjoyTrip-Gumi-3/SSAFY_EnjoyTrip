package com.ssafy.trip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.trip.model.dto.PopularAttractionDTO;

@Mapper
public interface LikeDao {
	
	/**
	 * 전체 관광지(attractionNo)중에서 좋아요 많은 순서대로 정렬한 결과를 반환합니다.
	 * 
	 * @return 좋아요가 많은 순서대로 정렬된 관광지 정보 반환
	 * */
	List<PopularAttractionDTO> selectPopularAttractions();
	
	/**
     * 특정 관광지(attractionNo)에 대한 총 좋아요(Like) 수를 반환합니다.
     * 
     * @param attractionNo 좋아요 수를 계산할 관광지의 식별자
     * @return 해당 attractionNo에 기록된 좋아요 행의 개수 (long)
     */
	long countLikesByAttraction(
			@Param("attractionNo") int attractionNo
			);
	
	/**
	* 사용자가 특정 관광지에 좋아요를 추가(INSERT)합니다.
	* 
	* @param userNo       좋아요를 누른 사용자의 식별자
	* @param attractionNo 좋아요 대상 관광지의 식별자
	* @return 삽입된 행(row)의 수 (성공 시 1)
	*/
	int insertLike(
			@Param("userNo") int userNo, 
			@Param("attractionNo") int attractionNo,
			@Param("groupName") String groupName
			);
	
	/**
     * 사용자가 특정 관광지에 눌렀던 좋아요를 취소(DELETE)합니다.
     * 
     * @param userNo       좋아요 취소할 사용자의 식별자
     * @param attractionNo 좋아요 취소 대상 관광지의 식별자
     * @return 삭제된 행(row)의 수 (성공 시 1)
     */
	int deleteLike(
			@Param("userNo") int userNo, 
			@Param("attractionNo") int attractionNo,
			@Param("groupName") String groupName
			);
	
	/**
     * 사용자가 이미 해당 관광지에 좋아요를 눌렀는지 확인합니다.
     * 
     * @param userNo       확인할 사용자의 식별자
     * @param attractionNo 확인할 관광지의 식별자
     * @return 존재하는 좋아요 레코드 수 (0이면 없음, >0이면 이미 좋아요 상태)
     */
	int existsLike(
			@Param("userNo") int userNo, 
			@Param("attractionNo") int attractionNo,
			@Param("groupName") String groupName
			);
}
