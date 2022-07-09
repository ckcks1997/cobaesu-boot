# 내용정리
## ch.02 JPA
### Pageable 인터페이스
이 인터페이스를 구현하기 위해서는 PageRequest라는 클래스를 통해 
구현이 가능한데 특이하게도 생성자가 protected로 선언되어 있어
static매서드인 of()를 이용해 처리한다.

```java

//1페이지 10개
Pageable p = PageRequest.of(0,10);
Page<Memo> result = memoRepository.findAll(p);
        
//생성되는 쿼리
        Hibernate:
        select
        memo0_.mno as mno1_0_,
        memo0_.memo_text as memo_tex2_0_
        from
        memo memo0_ 
             limit ?
        
//다음과 같은 정렬 조건 추가도 된다.
Sort sort = Sort.by("mno").descending();
Pageable p = PageRequest.of(0,10, sort);

//그냥 PageRequest를 만들어서 념길수도 있다.
//page, size, sort.direction
 PageRequest pageRequest =
        PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

 Page<Member> page = memberRepository.findByAge(age, pageRequest);
 
 
```
-------
## ch.04
#### Auditing
엔티티에서 상속받아 사용, 날짜 등을 자동으로 삽입해줌.

메인 클래스에서 ```@EnableJpaAuditing```를 선언 후 다음 클래스를 만든다. 
```java
@EntityListeners(AuditingEntityListener.class)//JPA Entity에 이벤트가 발생할 때 콜백을 처리하고 코드를 실행하는 방법
@MappedSuperclass //모 클래스에 선언하고 속성만 상속 받아서 사용하고 싶을 때
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}

```
그 다음 해당 클래스를 상속받으면 끝.
```java
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity{

    @Id
    @GeneratedValue
    private Long gno;

    @Column(length = 100, nullable = false)
    private String title;
    
    ...
```


