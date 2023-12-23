package chaps.dev.customnavigationdrawer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import chaps.dev.customnavigationdrawer.databinding.ItemMenuNavigationBinding

class MenuNavigationAdapter(
    private var menuList: List<Menu>,
    private val onItemMenuClickListener: OnItemClickListener
): RecyclerView.Adapter<MenuNavigationAdapter.ViewHolder>() {

    private var selectedMenu = 0

    inner class ViewHolder(private val binding: ItemMenuNavigationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Menu, position: Int) {
            binding.apply {
                titleMenu.text = item.title

                if (position == selectedMenu) {
                    cardView.setCardBackgroundColor(ContextCompat.getColor(root.context, R.color.green_200))
                    iconMenu.setImageResource(getFilledIconResource(item.icon))
                    titleMenu.setTextColor(ContextCompat.getColor(root.context, R.color.white))
                } else {
                    cardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
                    iconMenu.setImageResource(getOutlinedIconResource(item.icon))
                    titleMenu.setTextColor(ContextCompat.getColor(root.context, R.color.grey_400))
                }
            }
        }

        private fun getOutlinedIconResource(icon: String): Int {
            return when (icon) {
                "&#xf0e4;" -> R.drawable.ic_dashboard_outlined
                "&#xf019;" -> R.drawable.ic_list_outlined
                "&#xf067;" -> R.drawable.ic_plus_outlined
                "&#xf1f8;" -> R.drawable.ic_trash_outlined
                "&#xf1e0;" -> R.drawable.ic_share_outlined
                "&#xf0c0;" -> R.drawable.ic_people_outlined
                "&#xf0f6;" -> R.drawable.ic_file_outlined
                "&#xf084;" -> R.drawable.ic_key_outlined
                "&#xf0c9;" -> R.drawable.ic_menu_outlined
                "&#xf007;" -> R.drawable.ic_user_outlined
                else -> (R.drawable.ic_settings_outlined)
            }
        }

        private fun getFilledIconResource(icon: String): Int {
            return when (icon) {
                "&#xf0e4;" -> R.drawable.ic_dashboard_fill
                "&#xf019;" -> R.drawable.ic_list_fill
                "&#xf067;" -> R.drawable.ic_plus_fill
                "&#xf1f8;" -> R.drawable.ic_trash_fill
                "&#xf1e0;" -> R.drawable.ic_share_fill
                "&#xf0c0;" -> R.drawable.ic_people_fill
                "&#xf0f6;" -> R.drawable.ic_file_fill
                "&#xf084;" -> R.drawable.ic_key_fill
                "&#xf0c9;" -> R.drawable.ic_menu_fill
                "&#xf007;" -> R.drawable.ic_user_fill
                else -> (R.drawable.ic_settings_fill)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuNavigationAdapter.ViewHolder {
        val binding = ItemMenuNavigationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuNavigationAdapter.ViewHolder, position: Int) {
        val item = menuList[position]
        holder.bind(item, position)
        holder.itemView.setOnClickListener {
            if (selectedMenu != position) {
                val previousSelectedMenu = selectedMenu
                selectedMenu = holder.adapterPosition
                notifyItemChanged(previousSelectedMenu)
                notifyItemChanged(selectedMenu)
                onItemMenuClickListener.onItemMenuClick(item.slug)
            }
        }

        if (position == selectedMenu) {
            onItemMenuClickListener.onItemMenuClick(item.slug)
        }
    }


    override fun getItemCount(): Int {
        return menuList.size
    }
}