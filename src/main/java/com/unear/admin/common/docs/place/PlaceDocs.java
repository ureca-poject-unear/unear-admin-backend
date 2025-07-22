package com.unear.admin.common.docs.place;

import com.unear.admin.places.dto.responsedto   .PlaceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

public class PlaceDocs {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation(
            summary = "제휴처 등록",
            description = "제휴처 매장을 등록합니다."
    )
    public @interface PostPlace {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation (
            summary = "제휴처 전체 조회",
            description = "제휴된 제휴처를 전체 조회합니다."
    )
    @ApiResponse(
        responseCode = "200",
        description = "제휴처 목록 조회 성공",
            content = @Content (
                    mediaType = "application/json",
                    schema = @Schema(implementation = PlaceResponseDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "ReadPlace",
                                    summary = "제휴처 목록 조회",
                                    value = """
                                            {
                                                "resultCode": 200,
                                                "codeName": "SUCCESS",
                                                "message": "요청이 성공적으로 처리되었습니다.",
                                                "data": {
                                                    "content": [
                                                        {
                                                            "id": 248,
                                                            "name": "브레댄코 광명센트럴자이점",
                                                            "address": "\\t\\n경기도 광명시 광명력로 28, 근린생활시설동 1층 101,102,103호 (일직동,광명역센트럴자이)",
                                                            "tel": "080-855-5532",
                                                            "latitude": 37.4200000,
                                                            "longitude": 126.8900000
                                                        },
                                                        {
                                                            "id": 219,
                                                            "name": "브레댄코 고속터미널점",
                                                            "address": "서울특별시 서초구 신반포로 188 지하철 역사 내 환승통로",
                                                            "tel": "080-855-5532",
                                                            "latitude": 37.5000000,
                                                            "longitude": 127.0000000
                                                        },
                                                        {
                                                            "id": 153,
                                                            "name": "제주민속촌",
                                                            "address": "제주 서귀포시 표선면 민속해안로 631-34",
                                                            "tel": "064-787-4501\\n064-787-4502",
                                                            "latitude": 33.3200000,
                                                            "longitude": 126.8400000
                                                        },
                                                        {
                                                            "id": 405,
                                                            "name": "아쿠아필드",
                                                            "address": "아쿠아필드 안성점 (경기도 안성시 공도읍 서동대로 3930-39 스타필드 안성 3층)",
                                                            "tel": "080-234-7552",
                                                            "latitude": 37.4400000,
                                                            "longitude": 127.0100000
                                                        },
                                                        {
                                                            "id": 190,
                                                            "name": "유앤아이피부과의원 산본점",
                                                            "address": "경기 군포시 산본천로 62 인베스텔 3층",
                                                            "tel": "1661-6020",
                                                            "latitude": 37.3600000,
                                                            "longitude": 126.9300000
                                                        },
                                                        {
                                                            "id": 299,
                                                            "name": "서울드래곤시티 강서점",
                                                            "address": "서울특별시 강서구 강서로45길 49-4, 상가동 1층 7호 (내발산동, 태승훼미리아파트)",
                                                            "tel": "02-2223-7000",
                                                            "latitude": 37.5500000,
                                                            "longitude": 126.8400000
                                                        },
                                                        {
                                                            "id": 298,
                                                            "name": "라그릴리아 등촌점",
                                                            "address": "서울특별시 강서구 공항대로41길 34, 1층 101호 (등촌동, 3동 플러스존)",
                                                            "tel": "080-731-2027",
                                                            "latitude": 37.5600000,
                                                            "longitude": 126.8500000
                                                        },
                                                        {
                                                            "id": 314,
                                                            "name": "달콤이네",
                                                            "address": "서울특별시 서대문구 거북골로20길 41 1층 상가",
                                                            "tel": "010-4275-1332",
                                                            "latitude": 37.5800000,
                                                            "longitude": 126.9100000
                                                        },
                                                        {
                                                            "id": 284,
                                                            "name": "뚜레쥬르 화곡서광점",
                                                            "address": "서울특별시 강서구 화곡로 292, 1층 104호 (화곡동, 6동 서광프리메라)",
                                                            "tel": "1577-0700",
                                                            "latitude": 37.5500000,
                                                            "longitude": 126.8500000
                                                        },
                                                        {
                                                            "id": 315,
                                                            "name": "늘봄카페",
                                                            "address": "서울특별시 강동구 성내로 10길 9-14",
                                                            "tel": "02-6494-0824",
                                                            "latitude": 37.5300000,
                                                            "longitude": 127.1300000
                                                        },
                                                        {
                                                            "id": 144,
                                                            "name": "GS25 곰달래자활센터점",
                                                            "address": "서울특별시 강서구 곰달래로 180, 1층 (화곡동, 2동 서울강서지역자활센터)",
                                                            "tel": "080-855-5525",
                                                            "latitude": 37.5300000,
                                                            "longitude": 126.8500000
                                                        },
                                                        {
                                                            "id": 158,
                                                            "name": "롯데월드 어드벤처 부산",
                                                            "address": "부산 기장군 기장읍 동부산관광로 42",
                                                            "tel": "1600-2000",
                                                            "latitude": 35.1900000,
                                                            "longitude": 129.2100000
                                                        },
                                                        {
                                                            "id": 369,
                                                            "name": "해담촌",
                                                            "address": "광주 북구 참판로 91",
                                                            "tel": "062-268-6692\\n",
                                                            "latitude": 37.3200000,
                                                            "longitude": 126.8500000
                                                        },
                                                        {
                                                            "id": 331,
                                                            "name": "정성베이커리",
                                                            "address": "서울특별시 종로구 창경궁로 241(명륜2가)",
                                                            "tel": "02-766-0691",
                                                            "latitude": 37.5800000,
                                                            "longitude": 127.0000000
                                                        },
                                                        {
                                                            "id": 330,
                                                            "name": "리치몬드과자점 성산본점",
                                                            "address": "서울특별시 마포구 월드컵북로 86 (성산동1층)",
                                                            "tel": "02-3142-7494",
                                                            "latitude": 37.5600000,
                                                            "longitude": 126.9100000
                                                        },
                                                        {
                                                            "id": 328,
                                                            "name": "나폴레옹제과",
                                                            "address": "서울특별시 송파구 백제고분로 67 위너스오피스텔2 1층",
                                                            "tel": "02-415-1144",
                                                            "latitude": 37.5100000,
                                                            "longitude": 127.0800000
                                                        },
                                                        {
                                                            "id": 261,
                                                            "name": "브레댄코 판교대장점",
                                                            "address": "\\t\\n경기 성남시 분당구 판교대장로7길 5 1층",
                                                            "tel": "080-855-5532",
                                                            "latitude": 37.3700000,
                                                            "longitude": 127.0700000
                                                        },
                                                        {
                                                            "id": 283,
                                                            "name": "파리바게뜨 등촌점",
                                                            "address": "서울특별시 강서구 공항대로41길 51, 1층 129호 (등촌동, 3동 세신그린코아)",
                                                            "tel": "080-731-2027",
                                                            "latitude": 37.5600000,
                                                            "longitude": 126.8500000
                                                        },
                                                        {
                                                            "id": 282,
                                                            "name": "파리바게뜨 마곡점",
                                                            "address": "서울특별시 강서구 공항대로61길 42 (염창동,(지상1층))",
                                                            "tel": "080-731-2027",
                                                            "latitude": 37.5500000,
                                                            "longitude": 126.8700000
                                                        },
                                                        {
                                                            "id": 312,
                                                            "name": "망원동티라미수 본점",
                                                            "address": "서울특별시 마포구 포은로 14 수성빌딩 1층",
                                                            "tel": "02-324-7877\\n",
                                                            "latitude": 37.5500000,
                                                            "longitude": 126.9100000
                                                        }
                                                    ],
                                                    "pageable": {
                                                        "pageNumber": 0,
                                                        "pageSize": 20,
                                                        "sort": {
                                                            "empty": true,
                                                            "sorted": false,
                                                            "unsorted": true
                                                        },
                                                        "offset": 0,
                                                        "paged": true,
                                                        "unpaged": false
                                                    },
                                                    "last": false,
                                                    "totalPages": 17,
                                                    "totalElements": 324,
                                                    "size": 20,
                                                    "number": 0,
                                                    "sort": {
                                                        "empty": true,
                                                        "sorted": false,
                                                        "unsorted": true
                                                    },
                                                    "first": true,
                                                    "numberOfElements": 20,
                                                    "empty": false
                                                }
                                            }
                                            """
                            )
                    }
            )
    )

    public @interface GetAllPlace {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation (
            summary = "제휴처 수정",
            description = "제휴처를 수정합니다."
    )
    public @interface UpdatePlace {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation (
            summary = "제휴처 삭제",
            description = "제휴처를 삭제합니다."
    )
    public @interface DeletePlace {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Operation (
            summary = "이벤트 반경 내 제휴처 조회.",
            description = "이벤트 반경 내 제휴처들을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "제휴처 목록 조회 성공",
            content = @Content (
                    mediaType = "application/json",
                    schema = @Schema(implementation = PlaceResponseDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "이벤트 지역 반경 내 제휴처 조회",
                                    summary = "이벤드 지경 반경 조회",
                                    value = """
                                            {
                                                "resultCode": 200,
                                                "codeName": "SUCCESS",
                                                "message": "요청이 성공적으로 처리되었습니다.",
                                                "data": [
                                                    {
                                                        "id": 298,
                                                        "name": "라그릴리아 등촌점",
                                                        "address": "서울특별시 강서구 공항대로41길 34, 1층 101호 (등촌동, 3동 플러스존)",
                                                        "tel": "080-731-2027",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8500000
                                                    },
                                                    {
                                                        "id": 283,
                                                        "name": "파리바게뜨 등촌점",
                                                        "address": "서울특별시 강서구 공항대로41길 51, 1층 129호 (등촌동, 3동 세신그린코아)",
                                                        "tel": "080-731-2027",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8500000
                                                    },
                                                    {
                                                        "id": 138,
                                                        "name": "GS25 화곡61길점",
                                                        "address": "서울특별시 강서구 화곡로61길 31, 1층 (등촌동, 3동)",
                                                        "tel": "080-855-5525",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8500000
                                                    },
                                                    {
                                                        "id": 141,
                                                        "name": "GS25 내발산대로점",
                                                        "address": "서울특별시 강서구 공항대로42길 16, 1층 (내발산동)",
                                                        "tel": "080-855-5525",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 297,
                                                        "name": "라그릴리아 NC등촌점",
                                                        "address": "서울특별시 강서구 강서로56길 17, 엔씨백화점 9층 (등촌동)",
                                                        "tel": "080-731-2027",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 393,
                                                        "name": "혜정닭갈비",
                                                        "address": "강원 춘천시 금강로62번길 11-1",
                                                        "tel": "033-256-5910",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 176,
                                                        "name": "유앤아이피부과의원 마곡점",
                                                        "address": "서울특별시 강서구 공항대로 168 747타워 3층",
                                                        "tel": "1661-6020",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8300000
                                                    },
                                                    {
                                                        "id": 455,
                                                        "name": "강서 팝업스토어",
                                                        "address": "서울 마포구 와우산로 29길",
                                                        "tel": "02-1234-5678",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 456,
                                                        "name": "강서 팝업스토어",
                                                        "address": "서울 마포구 와우산로 29길",
                                                        "tel": "02-1234-5678",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 457,
                                                        "name": "강서 팝업스토어",
                                                        "address": "서울 마포구 와우산로 29길",
                                                        "tel": "02-1234-5678",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 458,
                                                        "name": "강서 팝업스토어",
                                                        "address": "서울 마포구 와우산로 29길",
                                                        "tel": "02-1234-5678",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 459,
                                                        "name": "강서 팝업스토어",
                                                        "address": "서울 마포구 와우산로 29길",
                                                        "tel": "02-1234-5678",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 444,
                                                        "name": "언에어 팝업스토어 강서점",
                                                        "address": "서울특별시 강서구 강서로 381",
                                                        "tel": "02-0000-0000",
                                                        "latitude": 37.5595542,
                                                        "longitude": 126.8381974
                                                    },
                                                    {
                                                        "id": 460,
                                                        "name": "강서 팝업스토어",
                                                        "address": "서울 마포구 와우산로 29길",
                                                        "tel": "02-1234-5678",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 445,
                                                        "name": "언에어 팝업스토어 강서점",
                                                        "address": "서울특별시 강서구 강서로 381",
                                                        "tel": "02-0000-0000",
                                                        "latitude": 37.5595542,
                                                        "longitude": 126.8381974
                                                    },
                                                    {
                                                        "id": 446,
                                                        "name": "언에어 팝업스토어 강서점",
                                                        "address": "서울특별시 강서구 강서로 381",
                                                        "tel": "02-0000-0000",
                                                        "latitude": 37.5595542,
                                                        "longitude": 126.8381974
                                                    },
                                                    {
                                                        "id": 439,
                                                        "name": "언에어 팝업스토어 강서점",
                                                        "address": "서울특별시 강서구 강서로 381",
                                                        "tel": "02-0000-0000",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 440,
                                                        "name": "언에어 팝업스토어 강서점",
                                                        "address": "서울특별시 강서구 강서로 381",
                                                        "tel": "02-0000-0000",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 441,
                                                        "name": "언에어 팝업스토어 강서점",
                                                        "address": "서울특별시 강서구 강서로 381",
                                                        "tel": "02-0000-0000",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 442,
                                                        "name": "언에어 팝업스토어 강서점",
                                                        "address": "서울특별시 강서구 강서로 381",
                                                        "tel": "02-0000-0000",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 443,
                                                        "name": "언에어 팝업스토어 강서점",
                                                        "address": "서울특별시 강서구 강서로 381",
                                                        "tel": "02-0000-0000",
                                                        "latitude": 37.5600000,
                                                        "longitude": 126.8400000
                                                    },
                                                    {
                                                        "id": 447,
                                                        "name": "언에어 팝업스토어 강서점",
                                                        "address": "서울특별시 강서구 강서로 381",
                                                        "tel": "02-0000-0000",
                                                        "latitude": 37.5595542,
                                                        "longitude": 126.8381974
                                                    }
                                                ]
                                            }
                                            """
                            )
                    }
            )
    )
    public @interface GetEventRadius {
    }
}
