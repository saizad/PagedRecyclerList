package sa.zad.pagedrecyclerlistexample.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import sa.zad.pagedrecyclerlist.AppListAdapter;

public class Items implements AppListAdapter.CompareItem<Items> {
    @SerializedName("kind")
    public String kind;
    @SerializedName("data")
    public Data data;

    @Override
    public boolean areItemsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
        return oldItem.data.id.equals(newItem.data.id);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof Items){
            return ((Items) obj).data.numComments == data.numComments;
        }
        return false;
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull Items oldItem, @NonNull Items newItem) {
        return null;
    }

    public static class LinkFlairRichtext {
    }

    public static class MediaEmbed {
    }

    public static class UserReports {
    }

    public static class SecureMediaEmbed {
    }

    public static class AuthorFlairRichtext {
    }

    public static class Gildings {
        @SerializedName("gid_1")
        public int gid1;
        @SerializedName("gid_2")
        public int gid2;
        @SerializedName("gid_3")
        public int gid3;
    }

    public static class Source {
        @SerializedName("url")
        public String url;
        @SerializedName("width")
        public int width;
        @SerializedName("height")
        public int height;
    }

    public static class Resolutions {
        @SerializedName("url")
        public String url;
        @SerializedName("width")
        public int width;
        @SerializedName("height")
        public int height;
    }

    public static class Variants {
    }

    public static class Images {
        @SerializedName("source")
        public Source source;
        @SerializedName("resolutions")
        public List<Resolutions> resolutions;
        @SerializedName("variants")
        public Variants variants;
        @SerializedName("id")
        public String id;
    }

    public static class Preview {
        @SerializedName("images")
        public List<Images> images;
        @SerializedName("enabled")
        public boolean enabled;
    }

    public static class ModReports {
    }

    public static class Data {

        @SerializedName("approved_at_utc")
        public String approvedAtUtc;
        @SerializedName("subreddit")
        public String subreddit;
        @SerializedName("selftext")
        public String selftext;
        @SerializedName("author_fullname")
        public String authorFullname;
        @SerializedName("saved")
        public boolean saved;
        @SerializedName("mod_reason_title")
        public String modReasonTitle;
        @SerializedName("gilded")
        public int gilded;
        @SerializedName("clicked")
        public boolean clicked;
        @SerializedName("title")
        public String title;
        @SerializedName("link_flair_richtext")
        public List<LinkFlairRichtext> linkFlairRichtext;
        @SerializedName("subreddit_name_prefixed")
        public String subredditNamePrefixed;
        @SerializedName("hidden")
        public boolean hidden;
        @SerializedName("pwls")
        public int pwls;
        @SerializedName("link_flair_css_class")
        public String linkFlairCssClass;
        @SerializedName("downs")
        public int downs;
        @SerializedName("thumbnail_height")
        public int thumbnailHeight;
        @SerializedName("hide_score")
        public boolean hideScore;
        @SerializedName("name")
        public String name;
        @SerializedName("quarantine")
        public boolean quarantine;
        @SerializedName("link_flair_text_color")
        public String linkFlairTextColor;
        @SerializedName("author_flair_background_color")
        public String authorFlairBackgroundColor;
        @SerializedName("subreddit_type")
        public String subredditType;
        @SerializedName("ups")
        public int ups;
        @SerializedName("domain")
        public String domain;
        @SerializedName("media_embed")
        public MediaEmbed mediaEmbed;
        @SerializedName("thumbnail_width")
        public int thumbnailWidth;
        @SerializedName("author_flair_template_id")
        public String authorFlairTemplateId;
        @SerializedName("is_original_content")
        public boolean isOriginalContent;
        @SerializedName("user_reports")
        public List<UserReports> userReports;
        @SerializedName("is_reddit_media_domain")
        public boolean isRedditMediaDomain;
        @SerializedName("is_meta")
        public boolean isMeta;
        @SerializedName("category")
        public String category;
        @SerializedName("secure_media_embed")
        public SecureMediaEmbed secureMediaEmbed;
        @SerializedName("link_flair_text")
        public String linkFlairText;
        @SerializedName("can_mod_post")
        public boolean canModPost;
        @SerializedName("score")
        public int score;
        @SerializedName("approved_by")
        public String approvedBy;
        @SerializedName("thumbnail")
        public String thumbnail;
        @SerializedName("author_flair_css_class")
        public String authorFlairCssClass;
        @SerializedName("author_flair_richtext")
        public List<AuthorFlairRichtext> authorFlairRichtext;
        @SerializedName("gildings")
        public Gildings gildings;
        @SerializedName("post_hint")
        public String postHint;
        @SerializedName("content_categories")
        public List<String> contentCategories;
        @SerializedName("is_self")
        public boolean isSelf;
        @SerializedName("mod_note")
        public String modNote;
        @SerializedName("created")
        public int created;
        @SerializedName("link_flair_type")
        public String linkFlairType;
        @SerializedName("wls")
        public int wls;
        @SerializedName("banned_by")
        public String bannedBy;
        @SerializedName("author_flair_type")
        public String authorFlairType;
        @SerializedName("contest_mode")
        public boolean contestMode;
        @SerializedName("selftext_html")
        public String selftextHtml;
        @SerializedName("likes")
        public String likes;
        @SerializedName("suggested_sort")
        public String suggestedSort;
        @SerializedName("banned_at_utc")
        public String bannedAtUtc;
        @SerializedName("view_count")
        public String viewCount;
        @SerializedName("archived")
        public boolean archived;
        @SerializedName("no_follow")
        public boolean noFollow;
        @SerializedName("is_crosspostable")
        public boolean isCrosspostable;
        @SerializedName("pinned")
        public boolean pinned;
        @SerializedName("over_18")
        public boolean over18;
        @SerializedName("preview")
        public Preview preview;
        @SerializedName("media_only")
        public boolean mediaOnly;
        @SerializedName("link_flair_template_id")
        public String linkFlairTemplateId;
        @SerializedName("can_gild")
        public boolean canGild;
        @SerializedName("spoiler")
        public boolean spoiler;
        @SerializedName("locked")
        public boolean locked;
        @SerializedName("author_flair_text")
        public String authorFlairText;
        @SerializedName("visited")
        public boolean visited;
        @SerializedName("num_reports")
        public String numReports;
        @SerializedName("distinguished")
        public String distinguished;
        @SerializedName("subreddit_id")
        public String subredditId;
        @SerializedName("mod_reason_by")
        public String modReasonBy;
        @SerializedName("removal_reason")
        public String removalReason;
        @SerializedName("link_flair_background_color")
        public String linkFlairBackgroundColor;
        @SerializedName("id")
        public String id;
        @SerializedName("is_robot_indexable")
        public boolean isRobotIndexable;
        @SerializedName("report_reasons")
        public String reportReasons;
        @SerializedName("author")
        public String author;
        @SerializedName("num_crossposts")
        public int numCrossposts;
        @SerializedName("num_comments")
        public int numComments;
        @SerializedName("send_replies")
        public boolean sendReplies;
        @SerializedName("whitelist_status")
        public String whitelistStatus;
        @SerializedName("mod_reports")
        public List<ModReports> modReports;
        @SerializedName("author_patreon_flair")
        public boolean authorPatreonFlair;
        @SerializedName("author_flair_text_color")
        public String authorFlairTextColor;
        @SerializedName("permalink")
        public String permalink;
        @SerializedName("parent_whitelist_status")
        public String parentWhitelistStatus;
        @SerializedName("stickied")
        public boolean stickied;
        @SerializedName("url")
        public String url;
        @SerializedName("subreddit_subscribers")
        public int subredditSubscribers;
        @SerializedName("created_utc")
        public int createdUtc;
        @SerializedName("is_video")
        public boolean isVideo;
    }
}
