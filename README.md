## Tech Stacks

- Spring Boot
- Spring Security
- Spring Data JPA
- Querydsl
- Lombok
- Mapstruct
- Postgresql

## Project Structure
```text
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── www
│   │   │           └── backend
│   │   │               ├── common
│   │   │               │   ├── dto
│   │   │               │   ├── entity
│   │   │               │   ├── exceptions
│   │   │               │   ├── repository
│   │   │               │   ├── response
│   │   │               │   └── util
│   │   │               ├── config
│   │   │               └── domain
│   │   │                   ├── artist
│   │   │                   │   ├── dto
│   │   │                   │   └── mapper
│   │   │                   ├── artwork
│   │   │                   ├── image
│   │   │                   └── video
│   │   └── resources
│   └── test
│       └── java
│           └── com
│               └── www
│                   └── backend
└── target
    ├── classes
    ├── generated-sources
    └──
```

## MapStruct Build
1. Mapper 정의
   backend/domain/artist와 같은 각 도메인 디렉토리 하위에 mapper 패키지가 위치한다.
   backend/domain/artist/mapper/ArtistMapper에서 사용할 도메인에 대한 매퍼를 정의해야 한다.

```java
@Mapper(componentModel = "spring")
public interface ArtistMapper {
    ArtistDto toDto(Artist artist);

    Artist toEntity(CreateArtistParameter createArtistParameter);

    void updateToEntity(UpdateArtistParameter updateArtistParameter, @MappingTarget Artist artist);
}
```
위 코드를 예시로 들어 설명하면 다음과 같다.
여기서 아래의 메소드 명들은 모두 고정된 형태가 아닌 원하는 이름으로 지정해서 사용이 가능하다.
1. toDto
    - Artist 엔티티를 파라미터로 받으면 ArtistDto 형태로 리턴해줌
2. toEntity
    - 전달받은 파라미터를 이용해 Artist 엔티티로 만들어서 리턴해줌
3. updateToEntity
    - 첫 번째 인자로 전달된 업데이트를 할 항목이 있는 파라미터와 업데이트를 할 대상인 Artist를 두 번째 인자로 전달하면 두 번째 인자에 첫 번째 인자로 들어온 파라미터에 대해서 매핑해준다.
    - 리턴 결과는 void

위와 같이 매퍼를 정의 한 후에 mvn copile 혹은 인텔리제이의 오른쪽 상단 메뉴에서 Maven Compile을 하면 target 디렉토리 하위에 빌드된 Mapper 결과물이 나온다.
결과물은 target/generated-sources 하위에 저장된다.

### 빌드 결과
빌드 된 결과물을 보면 Mapstruct는 결과적으로 단순하게 개발자가 생성자를 만들어서 일일히 매핑해주는 지루한 작업을 대신해주는 정도의 역할을 함
```java
    @Override
    public Artist toEntity(CreateArtistParameter createArtistParameter) {
        if ( createArtistParameter == null ) {
            return null;
        }

        Artist artist = new Artist();

        artist.setGenre( createArtistParameter.getGenre() );
        artist.setName( createArtistParameter.getName() );
        artist.setEmail( createArtistParameter.getEmail() );
        artist.setInstagramAccount( createArtistParameter.getInstagramAccount() );
        artist.setDescription( createArtistParameter.getDescription() );
        artist.setBio( createArtistParameter.getBio() );
        artist.setProfileImage( createArtistParameter.getProfileImage() );

        return artist;
    }

    @Override
    public void updateToEntity(UpdateArtistParameter updateArtistParameter, Artist artist) {
        if ( updateArtistParameter == null ) {
            return;
        }

        artist.setGenre( updateArtistParameter.getGenre() );
        artist.setName( updateArtistParameter.getName() );
        artist.setEmail( updateArtistParameter.getEmail() );
        artist.setInstagramAccount( updateArtistParameter.getInstagramAccount() );
        artist.setDescription( updateArtistParameter.getDescription() );
        artist.setBio( updateArtistParameter.getBio() );
        artist.setProfileImage( updateArtistParameter.getProfileImage() );
    }
```

## Querydsl Build
Querydsl은 어떤 엔티티를 생성하거나 Dto 클래스를 생성하는 경우 빌드하면 target 하위에 generated-sources/java 위치에 QClass가 생성된다.
간혹 mvn compile 명령어로 생성이 되지 않는 경우 pom.xml을 우클릭 하고 Maven > Generate Sources and Update Folders 메뉴를 클릭하면 생성된다.