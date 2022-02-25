package com.hs.util.commarkUtil;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

/**
 * @author Hs
 * @Date 2021/12/29 14:08
 */
public class MarkDownToHTMLUtil {
    // 标题锚点 允许将自动生成的"id"属性添加到标题标签,"id"基于标题的文本。 以便生成目录
    private static final Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
    // 表
    private static final List<Extension> tableExtension = Collections.singletonList(TablesExtension.create());
    // 任务列表
    private static final Set<Extension> taskListExtension = Collections.singleton(TaskListItemsExtension.create());
    // 删除线
    private static final Set<Extension> delExtension = Collections.singleton(StrikethroughExtension.create());

    private static final Parser parser=Parser.builder()
            .extensions(tableExtension)
            .extensions(taskListExtension)
            .extensions(delExtension)
            .build();

    private static final HtmlRenderer htmlRender=HtmlRenderer.builder()
            .extensions(headingAnchorExtensions)
            .extensions(tableExtension)
            .extensions(taskListExtension)
            .extensions(delExtension)
            .attributeProviderFactory(context -> new CustomAttributeProvider())
            .build();


    /**
     * 自定义标签的属性
     */
    private static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            //改变a标签的target属性为_blank
            if (node instanceof Link) {
                Link n = (Link) node;
                String destination = n.getDestination();
                //判断是否页内锚点跳转
                if (destination.startsWith("#")) {
                    attributes.put("class", "toc-link");//针对tocbot锚点跳转的class属性
                } else {
                    //外部链接
                    attributes.put("target", "_blank");
                    attributes.put("rel", "external nofollow noopener");
                }
            }
            if (node instanceof TableBlock) {
                attributes.put("class", "ui celled table");//针对 semantic-ui 的class属性
            }
            // 如果节点是代码块并且没有对应的language，将其设置为java
            if (node instanceof FencedCodeBlock) {
                String nodeClass = attributes.get("class");
                if (nodeClass==null||!nodeClass.contains("language")){
                    attributes.put("class","language-java");
                }
            }
        }
    }

    // markdown转html
    public static String markdownToHtml(String markdown) {
        Node document = parser.parse(markdown);
        return htmlRender.render(document);
    }

    public static void main(String[] args) {
        //Node document = parser.parse("This is *Sparta*");
        //HtmlRenderer renderer = HtmlRenderer.builder().build();
        //// "<p>This is <em>Sparta</em></p>\n"
        //System.out.println(renderer.render(document));
        System.out.println(markdownToHtml("https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2021/03/B01/001.png"));
    }

}
