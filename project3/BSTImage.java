package project3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Renders a binary tree (any BinNode<E>) to a PNG image.
 *
 * Layout: x = in-order position, y = depth. Nodes never overlap and
 * left/right ordering is preserved.
 */

// thanks claude
// https://claude.ai/share/85487f82-6672-4cce-bf66-1bc029d0c43b
public class BSTImage {

    private static final int RADIUS = 22;
    private static final int X_GAP = 56;
    private static final int Y_GAP = 80;
    private static final int MARGIN = 40;

    /**
     * Draws the tree rooted at `root` and saves it as a PNG at `outputPath`.
     * Assumes left()/right() return null when there is no child.
     */
    public static <E> void drawTree(BinNode<E> root, String outputPath) throws IOException {
        if (root == null) throw new IllegalArgumentException("Tree is empty");

        // pos.get(node) = {column (in-order index), depth}
        Map<BinNode<E>, int[]> pos = new IdentityHashMap<>();
        int[] counter = {0};
        int[] maxDepth = {0};
        assign(root, 0, counter, maxDepth, pos);

        int width = (counter[0] - 1) * X_GAP + 2 * MARGIN;
        int height = maxDepth[0] * Y_GAP + 2 * MARGIN;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("SansSerif", Font.BOLD, 15));

        drawEdges(g, root, pos);   // edges first so nodes sit on top of them
        drawNodes(g, root, pos);
        g.dispose();

        ImageIO.write(img, "png", new File(outputPath));
    }

    // In-order traversal assigns each node a column; depth gives the row.
    private static <E> void assign(BinNode<E> n, int depth, int[] counter, int[] maxDepth,
                                   Map<BinNode<E>, int[]> pos) {
        if (n == null) return;
        assign(n.left(), depth + 1, counter, maxDepth, pos);
        pos.put(n, new int[]{counter[0]++, depth});
        maxDepth[0] = Math.max(maxDepth[0], depth);
        assign(n.right(), depth + 1, counter, maxDepth, pos);
    }

    private static int px(int[] p) { return MARGIN + p[0] * X_GAP; }
    private static int py(int[] p) { return MARGIN + p[1] * Y_GAP; }

    private static <E> void drawEdges(Graphics2D g, BinNode<E> n, Map<BinNode<E>, int[]> pos) {
        if (n == null) return;
        int[] p = pos.get(n);
        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(2f));
        if (n.left() != null) {
            int[] c = pos.get(n.left());
            g.drawLine(px(p), py(p), px(c), py(c));
        }
        if (n.right() != null) {
            int[] c = pos.get(n.right());
            g.drawLine(px(p), py(p), px(c), py(c));
        }
        drawEdges(g, n.left(), pos);
        drawEdges(g, n.right(), pos);
    }

    private static <E> void drawNodes(Graphics2D g, BinNode<E> n, Map<BinNode<E>, int[]> pos) {
        if (n == null) return;
        int[] p = pos.get(n);
        int x = px(p), y = py(p);

        g.setColor(new Color(173, 216, 230));
        g.fillOval(x - RADIUS, y - RADIUS, RADIUS * 2, RADIUS * 2);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2f));
        g.drawOval(x - RADIUS, y - RADIUS, RADIUS * 2, RADIUS * 2);

        String s = String.valueOf(n.element());
        FontMetrics fm = g.getFontMetrics();
        g.drawString(s, x - fm.stringWidth(s) / 2, y + (fm.getAscent() - fm.getDescent()) / 2);

        drawNodes(g, n.left(), pos);
        drawNodes(g, n.right(), pos);
    }
}