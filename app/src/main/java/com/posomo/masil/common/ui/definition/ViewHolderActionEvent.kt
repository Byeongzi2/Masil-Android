package com.posomo.masil.common.ui.definition

import com.posomo.masil.common.ui.navigation.DeepLinkDestination
import com.posomo.masil.domain.model.constants.ViewType

sealed class ViewHolderActionEvent {
	data object Click : ViewHolderActionEvent()
	data class ViewMoreEvent(val destination: DeepLinkDestination) : ViewHolderActionEvent()
}

interface OnViewHolderAction {
	fun onAction(viewType: ViewType, actionEvent: ViewHolderActionEvent)
}