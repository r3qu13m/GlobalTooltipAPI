package r3qu13m.core.gtip;

import java.util.Iterator;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import cpw.mods.fml.relauncher.IClassTransformer;

public class GlobalTooltipAPITransformer implements IClassTransformer, Opcodes {

	private String transformClass_obf = "avf";
	private String transformMethod_obf = "a";
	private String transformMethodDesc_obf = "(Lur;II)V";

	private String transformClass = "net.minecraft.client.gui.inventory.GuiContainer";
	private String transformMethod = "drawItemStackTooltip";
	private String transformMethodDesc = "(Lnet/minecraft/item/ItemStack;II)V";

	@Override
	public byte[] transform(String name, byte[] bytes) {
		if (name.equals(transformClass)) {
			bytes = transformGuiContainer(bytes, false);
		} else if (name.equals(transformClass_obf)) {
			bytes = transformGuiContainer(bytes, true);
		}
		return bytes;
	}

	private byte[] transformGuiContainer(byte[] bytes, boolean isObf) {
		if (isObf) {
			return transformGuiContainer(bytes, transformClass_obf, transformMethod_obf, transformMethodDesc_obf, true);
		} else {
			return transformGuiContainer(bytes, transformClass, transformMethod, transformMethodDesc, false);
		}
	}

	private byte[] transformGuiContainer(byte[] bytes, String cls, String method, String desc, boolean isObf) {
		ClassNode cn = new ClassNode();
		ClassReader cr = new ClassReader(bytes);
		cr.accept(cn, 0);
		for (MethodNode mn : (List<MethodNode>) cn.methods) {
			if (mn.name.equals(method) && mn.desc.equals(desc)) {
				InsnList append_insn = new InsnList();
				append_insn.add(new VarInsnNode(ALOAD, 1));
				append_insn.add(new VarInsnNode(ALOAD, 4));
				append_insn.add(new MethodInsnNode(INVOKESTATIC, "r3qu13m/api/GlobalTooltip", "addList",
						String.format("(L%s;Ljava/util/List;)V", isObf ? "ur" : "net/minecraft/item/ItemStack"),
						false));
				Iterator<AbstractInsnNode> it = mn.instructions.iterator();
				while (it.hasNext()) {
					AbstractInsnNode t = it.next();
					if (t.getOpcode() == ASTORE) {
						VarInsnNode insn = (VarInsnNode) t;
						if (insn.var == 4) {
							mn.instructions.insert(t, append_insn);
							ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
							cn.accept(cw);
							bytes = cw.toByteArray();
							break;
						}
					}
				}
				break;
			}
		}
		return bytes;
	}
}
