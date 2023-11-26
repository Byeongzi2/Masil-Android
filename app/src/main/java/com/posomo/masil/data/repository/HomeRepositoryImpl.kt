package com.posomo.masil.data.repository

import com.posomo.masil.MasilApplication
import com.posomo.masil.common.ui.navigation.DeepLinkDestination
import com.posomo.masil.domain.model.CommonVO
import com.posomo.masil.domain.model.Store
import com.posomo.masil.domain.model.constants.ViewType
import com.posomo.masil.domain.model.content.BannerImageInfoVO
import com.posomo.masil.domain.model.content.HomeBannerVO
import com.posomo.masil.domain.model.content.SectionVO
import com.posomo.masil.domain.model.content.SpacerVO
import com.posomo.masil.domain.repository.HomeRepository
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
) : HomeRepository {
	override fun fetchHomeData() = flow {
		val fakeHomeBanner = HomeBannerVO(
			title = "고요한 가을 저녁, 추천 공간",
			subTitle = "하루를 조용히 마무리할 수 있는 공간을 소개해요.",
			imageInfoList = listOf(
				BannerImageInfoVO(
					imageTitle = "혼술과 책의 공간, 파이키",
					imageUrl = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
					blurImageUrl = "data:image/jpeg;base64,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",
					hashTag = "#조용한 #칵테일 한 잔 #여유",
					location = "강남구"
				),
				BannerImageInfoVO(
					imageTitle = "혼술과 책의 공간, 파이키",
					imageUrl = "https://dimg.donga.com/wps/NEWS/IMAGE/2022/01/28/111500268.2.jpg",
					blurImageUrl = "https://images.unsplash.com/photo-1503614472-8c93d56e92ce?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D",
					hashTag = "#조용한 #칵테일 한 잔 #여유",
					location = "강남구"
				),
				BannerImageInfoVO(
					imageTitle = "혼술과 책의 공간, 파이키",
					imageUrl = "https://cdn.imweb.me/upload/S201910012ff964777e0e3/62f9a36ea3cea.jpg",
					blurImageUrl = "https://previews.123rf.com/images/yelenayemchuk/yelenayemchuk1407/yelenayemchuk140700004/29793562-%EC%97%AC%EB%A6%84-%ED%92%8D%EA%B2%BD.jpg",
					hashTag = "#조용한 #칵테일 한 잔 #여유",
					location = "강남구"
				),
				BannerImageInfoVO(
					imageTitle = "혼술과 책의 공간, 파이키",
					imageUrl = "https://images.mypetlife.co.kr/content/uploads/2021/10/19151330/corgi-g1a1774f95_1280-1024x682.jpg",
					blurImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTp4skLpm7Pm0WklKN6oXijM8Q1iogJOXycyJI3nwEJOgpB1Yyh5oUTtBWWjpUh1DNmjMk&usqp=CAU",
					hashTag = "#조용한 #칵테일 한 잔 #여유",
					location = "강남구"
				),
				BannerImageInfoVO(
					imageTitle = "혼술과 책의 공간, 파이키",
					imageUrl = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
					blurImageUrl = "https://images.unsplash.com/photo-1503614472-8c93d56e92ce?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D",
					hashTag = "#조용한 #칵테일 한 잔 #여유",
					location = "강남구"
				),
				BannerImageInfoVO(
					imageTitle = "혼술과 책의 공간, 파이키",
					imageUrl = "https://dimg.donga.com/wps/NEWS/IMAGE/2022/01/28/111500268.2.jpg",
					blurImageUrl = "https://previews.123rf.com/images/yelenayemchuk/yelenayemchuk1407/yelenayemchuk140700004/29793562-%EC%97%AC%EB%A6%84-%ED%92%8D%EA%B2%BD.jpg",
					hashTag = "#조용한 #칵테일 한 잔 #여유",
					location = "강남구"
				),
				BannerImageInfoVO(
					imageTitle = "혼술과 책의 공간, 파이키",
					imageUrl = "https://images.mypetlife.co.kr/content/uploads/2021/10/19151330/corgi-g1a1774f95_1280-1024x682.jpg",
					blurImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTp4skLpm7Pm0WklKN6oXijM8Q1iogJOXycyJI3nwEJOgpB1Yyh5oUTtBWWjpUh1DNmjMk&usqp=CAU",
					hashTag = "#조용한 #칵테일 한 잔 #여유",
					location = "강남구"
				),
			)
		)
		val fakeStoreList = listOf(
			Store(
				id = 100L,
				imageUrl = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
				isBookmarked = false,
				name = "{주점명1}",
				location = "{지역}",
				category = "{업태}"
			),
			Store(
				id = 101L,
				imageUrl = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
				isBookmarked = false,
				name = "{주점명2}",
				location = "{지역}",
				category = "{업태}"
			),
			Store(
				id = 102L,
				imageUrl = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
				isBookmarked = false,
				name = "{주점명3}",
				location = "{지역}",
				category = "{업태}"
			),
			Store(
				id = 103L,
				imageUrl = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
				isBookmarked = false,
				name = "{주점명4}",
				location = "{지역}",
				category = "{업태}"
			),
			Store(
				id = 104L,
				imageUrl = "https://image.dongascience.com/Photo/2020/03/5bddba7b6574b95d37b6079c199d7101.jpg",
				isBookmarked = false,
				name = "{주점명5}",
				location = "{지역}",
				category = "{업태}"
			),
		)
		val fakeHomeData = listOf<CommonVO>(
			CommonVO(
				id = 0L,
				viewType = ViewType.HomeBannerViewType,
				content = fakeHomeBanner
			),
			CommonVO(
				id = 1L,
				viewType = ViewType.SpacerViewType,
				content = SpacerVO(height = 60)
			),
			CommonVO(
				id = 2L,
				viewType = ViewType.SectionViewType,
				content = SectionVO(
					title = "Title1",
					viewMoreDestination = DeepLinkDestination.findDeepLinkDestination(MasilApplication.getApplicationContext(), "masil://temp1"),
					storeList = fakeStoreList
				)
			),
			CommonVO(
				id = 3L,
				viewType = ViewType.SpacerViewType,
				content = SpacerVO(height = 60)
			),
			CommonVO(
				id = 4L,
				viewType = ViewType.SectionViewType,
				content = SectionVO(
					title = "Title2",
					viewMoreDestination = DeepLinkDestination.findDeepLinkDestination(MasilApplication.getApplicationContext(), "masil://temp2"),
					storeList = fakeStoreList
				)
			),
		)
		emit(fakeHomeData)
	}
}