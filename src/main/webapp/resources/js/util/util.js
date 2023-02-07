// 넘어온 값이 빈값인지 체크합니다.
// !value 하면 생기는 논리적 오류를 제거하기 위해
// 명시적으로 value == 사용
// [], {} 도 빈값으로 처리
var isEmpty = function(value) {
	if (value == ""
			||  value == null
			||  value == undefined
			||  value == "undefined"
			|| (value != null && typeof value == "object" && !Object
					.keys(value).length)) {
		return true
	} else {
		return false
	}
};