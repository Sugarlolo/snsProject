package com.example.snsProject.controller;

import com.example.snsProject.model.DTO.PostAllDTO;
import com.example.snsProject.model.DTO.PostImageDTO;
import com.example.snsProject.service.FollowService;
import com.example.snsProject.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class HomeController {
    private final FollowService followService;
    private final PageService pageService;
    private List<PostAllDTO> posts;

    @GetMapping
    public String infiniteScrollPage(Model model) {
        return "infinite-scroll";
    }

    @GetMapping("/load-more-data")
    public String loadMoreData(@AuthenticationPrincipal UserDetails user, @RequestParam int nextPageLimit, @RequestParam int limit) {
        String htmlsPost = "";
        String htmlPost = "";
        try {
            posts = pageService.getPosts(user.getUsername(),nextPageLimit,nextPageLimit);
            for (PostAllDTO post: posts) {
                htmlPost = " <article id=\""+"Post"+post.getId()+"\">\n" +
                        "                                 <div class=\"home_feed_contents\">\n" +
                        "                                    <div class=\"home_feed_head\">\n" +
                        "                                       <div class=\"feed_head_box\">\n" +
                        "                                          <div class=\"feed_head_img_box1\">\n" +
                        "                                             <div>\n" +
                        "                                                <div class=\"feed_head_img_box2\">\n" +
                        "                                                   <a href=\"#\" class=\"feed_head_img\">\n" +
                        "                                                      <img alt=\"프로필 사진\" class=\"feed_head_profile_img\" src=\""+post.getUrl()+"\">\n" +
                        "                                                   </a>\n" +
                        "                                                </div>\n" +
                        "                                             </div>\n" +
                        "                                          </div>\n" +
                        "                                          <div class=\"feed_head_text_box1\">\n" +
                        "                                             <div class=\"feed_head_text_box2\">\n" +
                        "                                                <div>\n" +
                        "                                                   <div class=\"feed_head_nameInfo\">\n" +
                        "                                                      <a href=\"#\" class=\"feed_head_nameLink\">\n" +
                        "                                                         <span class=\"feed_head_name\">"+post.getName()+"</span>\n" +
                        "                                                      </a>\n" +
                        "                                                   </div>\n" +
                        "                                                </div>\n" +
                        "                                                <div class=\"feed_head_timeInfo\">\n" +
                        "                                                   <span class=\"dot_for_separate\">\n" +
                        "                                                      <span class=\"common_dot\">•</span>\n" +
                        "                                                   </span>\n" +
                        "                                                   <div class=\"feed_head_time_box\">\n" +
                        "                                                      <a class=\"feed_head_timeLink\">\n" +
                        "                                                         <span class=\"feed_head_time\">"+post.getDate()+"</span>\n" +
                        "                                                      </a>\n" +
                        "                                                   </div>\n" +
                        "                                                </div>\n" +
                        "                                             </div>\n" +
                        "                                          </div>\n" +
                        "                                          <div class=\"feed_head_more_box1\">\n" +
                        "                                             <div class=\"feed_head_more_box2\">\n" +
                        "                                                <div class=\"feed_head_morePopup\">\n" +
                        "                                                   <i class=\"fa-solid fa-ellipsis\"></i>\n" +
                        "                                                </div>\n" +
                        "                                             </div>\n" +
                        "                                          </div>\n" +
                        "                                       </div>\n" +
                        "                                    </div>\n" +
                        "                                    <div class=\"home_feed_body\">\n" +
                        "                                       <div class=\"feed_body_cont\">\n" +
                        "                                          <div class=\"feed_body_list\">";
                for (PostImageDTO image:post.getImages()) {
                    htmlPost += "<div class=\"feed_body_imgBox\" >\n" +
                            "<a class=\"feed_body_imgLink\">\n" +
                            "<img alt=\"#\" class=\"feed_body_img\" src=\"" + image.getUrl()+
                            "\"></a>\n" +
                            "</div>\n";
                }
                htmlPost += "  </div>\n" +
                        "                                       </div>\n" +
                        "                                    </div>\n" +
                        "                                    <div class=\"home_feed_footer\">\n" +
                        "                                       <div class=\"feed_footer_cont\">\n" +
                        "                                          <div class=\"feed_footer_actionBox\">\n" +
                        "                                             <div class=\"feed_footer_interactive\">\n" +
                        "                                                <div class=\"feed_footer_likeBox def_size\">\n" +
                        "                                                   <span class=\"like-icon\"><i class=\"" + (post.getLikeYN() == 1 ?  "fa-solid fa-heart\"" : "fa-regular fa-heart\"") +" data-post-id = \""+post.getId()+"\"" + "\"></i></span>\n" +
                        "                                                </div>\n" +
                        "                                                <div class=\"feed_footer_commentBox def_size\">\n" +
                        "                                                   <span><i class=\"fa-regular fa-comment fa-lg\" data-post-id = \""+post.getId()+"\"></i></span>\n" +
                        "                                                </div>\n" +
                        "                                                <div class=\"feed_footer_messageBox def_size\">\n" +
                        "                                                   <span><i class=\"fa-regular fa-paper-plane fa-lg\" data-post-id = \""+post.getId()+"\"></i></span>\n" +
                        "                                                </div>\n" +
                        "                                             </div>\n" +
                        "                                             <div class=\"feed_footer_bookmarkBox\">\n" +
                        "                                                <div class=\"feed_footer_bookmark def_size\">\n" +
                        "                                                   <span><i class=\""+(post.getBookmarkYN() > 0 ? "fa-solid fa-bookmark" : "fa-regular fa-bookmark" ) +"\"data-post-id = \""+post.getId()+"\"></i></span>\n" +
                        "                                                </div>\n" +
                        "                                             </div>\n" +
                        "                                          </div>\n" +
                        "                                          <div class=\"feed_footer_likeNumCont\">\n" +
                        "                                             <div class=\"feed_footer_likeNumBox\">\n" +
                        "                                                <span class=\"feed_footer_likeNumText\">\n" +
                        "                                                   좋아요\n" +
                        "                                                   <span class=\"feed_footer_likeNum\" id= \""+ "likeCount_"+post.getId().toString()+"\">"+post.getLikes().size()+"</span>만개\n" +
                        "                                                </span>\n" +
                        "                                             </div>\n" +
                        "                                          </div>\n" +
                        "                                          <div class=\"feed_footer_ownerCommentBox\">\n" +
                        "                                             <div class=\"feed_footer_ownerNameBox\">\n" +
                        "                                                <span class=\"feed_footer_ownerName\">\n" + post.getUserName()+
                        "                                                </span>\n" +
                        "                                             </div>\n" +
                        "                                             <div class=\"feed_footer_ownerTextBox\">\n" +
                        "                                                <span class=\"feed_footer_ownerText\">\n" + (post.getContent() != null ? post.getContent() : "") +
                        "                                                </span>\n" +
                        "                                             </div>\n" +
                        "                                          </div>\n" +
                        "                                          <div class=\"feed_footer_commentNumCont\">\n" +
                        "                                             <div class=\"feed_footer_commentNumBox\">\n" +
                        "                                                <span class=\"feed_footer_commentText\">\n" +
                        "                                                   댓글\n" +
                        "                                                   <span class=\"feed_footer_commentNum\">"+post.getCommentSize()+"</span>개 모두 보기\n" +
                        "                                                </span>\n" +
                        "                                             </div>\n" +
                        "                                          </div>\n" +
                        "                                          <div class=\"feed_footer_inputCont\">\n" +
                        "                                             <form id=\""+post.getId()+"\">\n" +
                        "                                                <div class=\"feed_footer_inputBox\">\n" +
                        "                                                   <div class=\"feed_footer_postCommentBox\">\n" +
                        "                                                      <input type=\"text\" class=\"feed_footer_postComment\" placeholder=\"댓글 달기..\">\n" +
                        "                                                      <input type=\"hidden\" id = \"postId\" value=\""+post.getId()+"\">\n" +
                        "                                                   </div>\n" +
                        "                                                   <div class=\"feed_footer_emoteButton\">\n" +
                        "                                                      <span><i class=\"fa-regular fa-face-smile\"></i></span>\n" +
                        "                                                   </div>\n" +
                        "                                                </div>\n" +
                        "                                             </form>\n" +
                        "                                          </div>\n" +
                        "                                       </div>\n" +
                        "                                    </div>\n" +
                        "                                 </div>\n" +
                        "                              </article>";
                htmlsPost += htmlPost;
                htmlPost = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return htmlsPost;
    }

    @RequestMapping("/followtest")
    public ResponseEntity<?> getFollowAllUser(@AuthenticationPrincipal UserDetails user) {
        String followList = "";
        try {
            followList = followService.followList(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(followList);
    }
    @RequestMapping("/followtest2")
    public ResponseEntity<?> getFollowAllUser2(@AuthenticationPrincipal UserDetails user) {
        String test = "";
        try {
            posts = pageService.getPosts(user.getUsername(), 0, 8);
            for (PostAllDTO post: posts) {
                if(post != null && post.getId() != null) {
                String postIdString = Long.toString(post.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(test);
    }
}
