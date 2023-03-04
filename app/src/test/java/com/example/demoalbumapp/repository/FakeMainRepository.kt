package com.example.demoalbumapp.repository

import com.example.demoalbumapp.remote.model.Albums
import com.example.demoalbumapp.remote.model.Photo
import com.example.demoalbumapp.remote.repository.MainRepository
import com.example.demoalbumapp.utils.Resource

class FakeMainRepository : MainRepository {
    override suspend fun getAlbums(): Resource<Albums> {
        val album = getAlbum()
        return if (album.isEmpty()){
            Resource.Error("no photos found")
        }else{
            Resource.Success(album)
        }

    }

    override suspend fun getPhoto(id: String): Resource<Photo> {
        val photo = getAlbum().find { it.id == id.toInt() }
        return if (photo != null) Resource.Success(photo) else Resource.Error("no photo found")
    }

    private fun getAlbum(): Albums {
        val photos = Albums()
        photos.add(
            Photo(
                1,
                1,
                "accusamus beatae ad facilis cum similique qui sunt",
                "https://via.placeholder.com/600/92c952",
                "https://via.placeholder.com/150/92c952"
            )
        )
        photos.add(
            Photo(
                1,
                2,
                "reprehenderit est deserunt velit ipsam",
                "https://via.placeholder.com/600/771796",
                "https://via.placeholder.com/150/771796"
            )
        )
        photos.add(
            Photo(
                1,
                3,
                "officia porro iure quia iusto qui ipsa ut modi",
                "https://via.placeholder.com/600/24f355",
                "https://via.placeholder.com/150/24f355"
            )
        )
        photos.add(
            Photo(
                2,
                58,
                "rem pariatur facere eaque",
                "https://via.placeholder.com/600/cde4c1",
                "https://via.placeholder.com/150/cde4c1"
            )
        )
        photos.add(
            Photo(
                2,
                59,
                "modi totam dolor eaque et ipsum est cupiditate",
                "https://via.placeholder.com/600/a46a91",
                "https://via.placeholder.com/150/a46a91"
            )
        )
        photos.add(
            Photo(
                2,
                60,
                "ea enim temporibus asperiores placeat consectetur commodi ullam",
                "https://via.placeholder.com/600/323599",
                "https://via.placeholder.com/150/323599"
            )
        )
        photos.add(
            Photo(
                3,
                110,
                "reiciendis et velit laborum recusandae",
                "https://via.placeholder.com/600/2f9e30",
                "https://via.placeholder.com/150/2f9e30"
            )
        )
        photos.add(
            Photo(
                3,
                111,
                "quos rem nulla ea amet",
                "https://via.placeholder.com/600/cc178e",
                "https://via.placeholder.com/150/cc178e"
            )
        )

        photos.add(
            Photo(
                3,
                112,
                "laudantium quibusdam inventore",
                "https://via.placeholder.com/600/170690",
                "https://via.placeholder.com/150/170690"
            )
        )

        return photos

    }
}