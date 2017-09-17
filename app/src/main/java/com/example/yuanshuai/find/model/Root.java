package com.example.yuanshuai.find.model;


/**
 * Created by 12917 on 2017/7/15.
 */

public class Root {
//    private int id;
//    private String name;
//    @JsonProperty("full_name")
//    private String fullName;
//    private Owner owner;
//    @JsonProperty("private")
//    private boolean pri;
//    @JsonProperty("html_url")
//    private String htmlUrl;
//    private String description;
//    private boolean fork;
//    private String url;
//    @JsonProperty("forks_url")
//    private String forksUrl;
//    @JsonProperty("keys_url")
//    private String keysUrl;
//    @JsonProperty("collaborators_url")
//    private String collaboratorsUrl;
//    @JsonProperty("teams_url")
//    private String teamsUrl;
//    @JsonProperty("hooks_url")
//    private String hooksUrl;
//    @JsonProperty("issue_events_url")
//    private String issueEventsUrl;
//    @JsonProperty("events_url")
//    private String eventsUrl;
//    @JsonProperty("assignees_url")
//    private String assigneesUrl;
//    @JsonProperty("branches_url")
//    private String branchesUrl;
//    @JsonProperty("tags_url")
//    private String tagsUrl;
//    @JsonProperty("blobs_url")
//    private String blobsUrl;
//    @JsonProperty("git_tags_url")
//    private String gitTagsUrl;
//    @JsonProperty("git_refs_url")
//    private String gitRefsUrl;
//    @JsonProperty("trees_url")
//    private String treesUrl;
//    @JsonProperty("statuses_url")
//    private String statusesUrl;
//    @JsonProperty("languages_url")
//    private String languagesUrl;
//    @JsonProperty("stargazers_url")
//    private String stargazersUrl;
//    @JsonProperty("contributors_url")
//    private String contributorsUrl;
//    @JsonProperty("subscribers_url")
//    private String subscribersUrl;
//    @JsonProperty("subscription_url")
//    private String subscriptionUrl;
//    @JsonProperty("commits_url")
//    private String commitsUrl;
//    @JsonProperty("git_commits_url")
//    private String gitCommitsUrl;
//    @JsonProperty("comments_url")
//    private String commentsUrl;
//    @JsonProperty("issue_comment_url")
//    private String issueCommentUrl;
//    @JsonProperty("contents_url")
//    private String contentsUrl;
//    @JsonProperty("compare_url")
//    private String compareUrl;
//    @JsonProperty("merges_url")
//    private String mergesUrl;
//    @JsonProperty("archive_url")
//    private String archiveUrl;
//    @JsonProperty("downloads_url")
//    private String downloadsUrl;
//    @JsonProperty("issues_url")
//    private String issuesUrl;
//    @JsonProperty("pulls_url")
//    private String pullsUrl;
//    @JsonProperty("milestones_url")
//    private String milestonesUrl;
//    @JsonProperty("notifications_url")
//    private String notificationsUrl;
//    @JsonProperty("labels_url")
//    private String labelsUrl;
//    @JsonProperty("releases_url")
//    private String releasesUrl;
//    @JsonProperty("deployments_url")
//    private String deploymentsUrl;
//    @JsonProperty("created_at")
//    private Date createdAt;
//    @JsonProperty("updated_at")
//    private Date updatedAt;
//    @JsonProperty("pushed_at")
//    private Date pushedAt;
//    @JsonProperty("git_url")
//    private String gitUrl;
//    @JsonProperty("ssh_url")
//    private String sshUrl;
//    @JsonProperty("clone_url")
//    private String cloneUrl;
//    @JsonProperty("svn_url")
//    private String svnUrl;
//    private String homepage;
//    private int size;
//    @JsonProperty("stargazers_count")
//    private int stargazersCount;
//    @JsonProperty("watchers_count")
//    private int watchersCount;
//    private String language;
//    @JsonProperty("has_issues")
//    private boolean hasIssues;
//    @JsonProperty("has_projects")
//    private boolean hasProjects;
//    @JsonProperty("has_downloads")
//    private boolean hasDownloads;
//    @JsonProperty("has_wiki")
//    private boolean hasWiki;
//    @JsonProperty("has_pages")
//    private boolean hasPages;
//    @JsonProperty("forks_count")
//    private int forksCount;
//    @JsonProperty("mirror_url")
//    private String mirrorUrl;
//    @JsonProperty("open_issues_count")
//    private int openIssuesCount;
//    private int forks;
//    @JsonProperty("open_issues")
//    private int openIssues;
//    private int watchers;
//    @JsonProperty("default_branch")
//    private String defaultBranch;
//    public void setId(int id) {
//        this.id = id;
//    }
//    public int getId() {
//        return id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//    public String getName() {
//        return name;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setOwner(Owner owner) {
//        this.owner = owner;
//    }
//    public Owner getOwner() {
//        return owner;
//    }
//
//    public void setPrivate(boolean pri) {
//        this.pri = pri;
//    }
//    public boolean getPrivate() {
//        return pri;
//    }
//
//    public void setHtmlUrl(String htmlUrl) {
//        this.htmlUrl = htmlUrl;
//    }
//    public String getHtmlUrl() {
//        return htmlUrl;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    public String getDescription() {
//        return description;
//    }
//
//    public void setFork(boolean fork) {
//        this.fork = fork;
//    }
//    public boolean getFork() {
//        return fork;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//    public String getUrl() {
//        return url;
//    }
//
//    public void setForksUrl(String forksUrl) {
//        this.forksUrl = forksUrl;
//    }
//    public String getForksUrl() {
//        return forksUrl;
//    }
//
//    public void setKeysUrl(String keysUrl) {
//        this.keysUrl = keysUrl;
//    }
//    public String getKeysUrl() {
//        return keysUrl;
//    }
//
//    public void setCollaboratorsUrl(String collaboratorsUrl) {
//        this.collaboratorsUrl = collaboratorsUrl;
//    }
//    public String getCollaboratorsUrl() {
//        return collaboratorsUrl;
//    }
//
//    public void setTeamsUrl(String teamsUrl) {
//        this.teamsUrl = teamsUrl;
//    }
//    public String getTeamsUrl() {
//        return teamsUrl;
//    }
//
//    public void setHooksUrl(String hooksUrl) {
//        this.hooksUrl = hooksUrl;
//    }
//    public String getHooksUrl() {
//        return hooksUrl;
//    }
//
//    public void setIssueEventsUrl(String issueEventsUrl) {
//        this.issueEventsUrl = issueEventsUrl;
//    }
//    public String getIssueEventsUrl() {
//        return issueEventsUrl;
//    }
//
//    public void setEventsUrl(String eventsUrl) {
//        this.eventsUrl = eventsUrl;
//    }
//    public String getEventsUrl() {
//        return eventsUrl;
//    }
//
//    public void setAssigneesUrl(String assigneesUrl) {
//        this.assigneesUrl = assigneesUrl;
//    }
//    public String getAssigneesUrl() {
//        return assigneesUrl;
//    }
//
//    public void setBranchesUrl(String branchesUrl) {
//        this.branchesUrl = branchesUrl;
//    }
//    public String getBranchesUrl() {
//        return branchesUrl;
//    }
//
//    public void setTagsUrl(String tagsUrl) {
//        this.tagsUrl = tagsUrl;
//    }
//    public String getTagsUrl() {
//        return tagsUrl;
//    }
//
//    public void setBlobsUrl(String blobsUrl) {
//        this.blobsUrl = blobsUrl;
//    }
//    public String getBlobsUrl() {
//        return blobsUrl;
//    }
//
//    public void setGitTagsUrl(String gitTagsUrl) {
//        this.gitTagsUrl = gitTagsUrl;
//    }
//    public String getGitTagsUrl() {
//        return gitTagsUrl;
//    }
//
//    public void setGitRefsUrl(String gitRefsUrl) {
//        this.gitRefsUrl = gitRefsUrl;
//    }
//    public String getGitRefsUrl() {
//        return gitRefsUrl;
//    }
//
//    public void setTreesUrl(String treesUrl) {
//        this.treesUrl = treesUrl;
//    }
//    public String getTreesUrl() {
//        return treesUrl;
//    }
//
//    public void setStatusesUrl(String statusesUrl) {
//        this.statusesUrl = statusesUrl;
//    }
//    public String getStatusesUrl() {
//        return statusesUrl;
//    }
//
//    public void setLanguagesUrl(String languagesUrl) {
//        this.languagesUrl = languagesUrl;
//    }
//    public String getLanguagesUrl() {
//        return languagesUrl;
//    }
//
//    public void setStargazersUrl(String stargazersUrl) {
//        this.stargazersUrl = stargazersUrl;
//    }
//    public String getStargazersUrl() {
//        return stargazersUrl;
//    }
//
//    public void setContributorsUrl(String contributorsUrl) {
//        this.contributorsUrl = contributorsUrl;
//    }
//    public String getContributorsUrl() {
//        return contributorsUrl;
//    }
//
//    public void setSubscribersUrl(String subscribersUrl) {
//        this.subscribersUrl = subscribersUrl;
//    }
//    public String getSubscribersUrl() {
//        return subscribersUrl;
//    }
//
//    public void setSubscriptionUrl(String subscriptionUrl) {
//        this.subscriptionUrl = subscriptionUrl;
//    }
//    public String getSubscriptionUrl() {
//        return subscriptionUrl;
//    }
//
//    public void setCommitsUrl(String commitsUrl) {
//        this.commitsUrl = commitsUrl;
//    }
//    public String getCommitsUrl() {
//        return commitsUrl;
//    }
//
//    public void setGitCommitsUrl(String gitCommitsUrl) {
//        this.gitCommitsUrl = gitCommitsUrl;
//    }
//    public String getGitCommitsUrl() {
//        return gitCommitsUrl;
//    }
//
//    public void setCommentsUrl(String commentsUrl) {
//        this.commentsUrl = commentsUrl;
//    }
//    public String getCommentsUrl() {
//        return commentsUrl;
//    }
//
//    public void setIssueCommentUrl(String issueCommentUrl) {
//        this.issueCommentUrl = issueCommentUrl;
//    }
//    public String getIssueCommentUrl() {
//        return issueCommentUrl;
//    }
//
//    public void setContentsUrl(String contentsUrl) {
//        this.contentsUrl = contentsUrl;
//    }
//    public String getContentsUrl() {
//        return contentsUrl;
//    }
//
//    public void setCompareUrl(String compareUrl) {
//        this.compareUrl = compareUrl;
//    }
//    public String getCompareUrl() {
//        return compareUrl;
//    }
//
//    public void setMergesUrl(String mergesUrl) {
//        this.mergesUrl = mergesUrl;
//    }
//    public String getMergesUrl() {
//        return mergesUrl;
//    }
//
//    public void setArchiveUrl(String archiveUrl) {
//        this.archiveUrl = archiveUrl;
//    }
//    public String getArchiveUrl() {
//        return archiveUrl;
//    }
//
//    public void setDownloadsUrl(String downloadsUrl) {
//        this.downloadsUrl = downloadsUrl;
//    }
//    public String getDownloadsUrl() {
//        return downloadsUrl;
//    }
//
//    public void setIssuesUrl(String issuesUrl) {
//        this.issuesUrl = issuesUrl;
//    }
//    public String getIssuesUrl() {
//        return issuesUrl;
//    }
//
//    public void setPullsUrl(String pullsUrl) {
//        this.pullsUrl = pullsUrl;
//    }
//    public String getPullsUrl() {
//        return pullsUrl;
//    }
//
//    public void setMilestonesUrl(String milestonesUrl) {
//        this.milestonesUrl = milestonesUrl;
//    }
//    public String getMilestonesUrl() {
//        return milestonesUrl;
//    }
//
//    public void setNotificationsUrl(String notificationsUrl) {
//        this.notificationsUrl = notificationsUrl;
//    }
//    public String getNotificationsUrl() {
//        return notificationsUrl;
//    }
//
//    public void setLabelsUrl(String labelsUrl) {
//        this.labelsUrl = labelsUrl;
//    }
//    public String getLabelsUrl() {
//        return labelsUrl;
//    }
//
//    public void setReleasesUrl(String releasesUrl) {
//        this.releasesUrl = releasesUrl;
//    }
//    public String getReleasesUrl() {
//        return releasesUrl;
//    }
//
//    public void setDeploymentsUrl(String deploymentsUrl) {
//        this.deploymentsUrl = deploymentsUrl;
//    }
//    public String getDeploymentsUrl() {
//        return deploymentsUrl;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setPushedAt(Date pushedAt) {
//        this.pushedAt = pushedAt;
//    }
//    public Date getPushedAt() {
//        return pushedAt;
//    }
//
//    public void setGitUrl(String gitUrl) {
//        this.gitUrl = gitUrl;
//    }
//    public String getGitUrl() {
//        return gitUrl;
//    }
//
//    public void setSshUrl(String sshUrl) {
//        this.sshUrl = sshUrl;
//    }
//    public String getSshUrl() {
//        return sshUrl;
//    }
//
//    public void setCloneUrl(String cloneUrl) {
//        this.cloneUrl = cloneUrl;
//    }
//    public String getCloneUrl() {
//        return cloneUrl;
//    }
//
//    public void setSvnUrl(String svnUrl) {
//        this.svnUrl = svnUrl;
//    }
//    public String getSvnUrl() {
//        return svnUrl;
//    }
//
//    public void setHomepage(String homepage) {
//        this.homepage = homepage;
//    }
//    public String getHomepage() {
//        return homepage;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//    public int getSize() {
//        return size;
//    }
//
//    public void setStargazersCount(int stargazersCount) {
//        this.stargazersCount = stargazersCount;
//    }
//    public int getStargazersCount() {
//        return stargazersCount;
//    }
//
//    public void setWatchersCount(int watchersCount) {
//        this.watchersCount = watchersCount;
//    }
//    public int getWatchersCount() {
//        return watchersCount;
//    }
//
//    public void setLanguage(String language) {
//        this.language = language;
//    }
//    public String getLanguage() {
//        return language;
//    }
//
//    public void setHasIssues(boolean hasIssues) {
//        this.hasIssues = hasIssues;
//    }
//    public boolean getHasIssues() {
//        return hasIssues;
//    }
//
//    public void setHasProjects(boolean hasProjects) {
//        this.hasProjects = hasProjects;
//    }
//    public boolean getHasProjects() {
//        return hasProjects;
//    }
//
//    public void setHasDownloads(boolean hasDownloads) {
//        this.hasDownloads = hasDownloads;
//    }
//    public boolean getHasDownloads() {
//        return hasDownloads;
//    }
//
//    public void setHasWiki(boolean hasWiki) {
//        this.hasWiki = hasWiki;
//    }
//    public boolean getHasWiki() {
//        return hasWiki;
//    }
//
//    public void setHasPages(boolean hasPages) {
//        this.hasPages = hasPages;
//    }
//    public boolean getHasPages() {
//        return hasPages;
//    }
//
//    public void setForksCount(int forksCount) {
//        this.forksCount = forksCount;
//    }
//    public int getForksCount() {
//        return forksCount;
//    }
//
//    public void setMirrorUrl(String mirrorUrl) {
//        this.mirrorUrl = mirrorUrl;
//    }
//    public String getMirrorUrl() {
//        return mirrorUrl;
//    }
//
//    public void setOpenIssuesCount(int openIssuesCount) {
//        this.openIssuesCount = openIssuesCount;
//    }
//    public int getOpenIssuesCount() {
//        return openIssuesCount;
//    }
//
//    public void setForks(int forks) {
//        this.forks = forks;
//    }
//    public int getForks() {
//        return forks;
//    }
//
//    public void setOpenIssues(int openIssues) {
//        this.openIssues = openIssues;
//    }
//    public int getOpenIssues() {
//        return openIssues;
//    }
//
//    public void setWatchers(int watchers) {
//        this.watchers = watchers;
//    }
//    public int getWatchers() {
//        return watchers;
//    }
//
//    public void setDefaultBranch(String defaultBranch) {
//        this.defaultBranch = defaultBranch;
//    }
//    public String getDefaultBranch() {
//        return defaultBranch;
//    }

}
