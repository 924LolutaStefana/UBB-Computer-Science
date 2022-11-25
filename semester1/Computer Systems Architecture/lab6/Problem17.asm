;17.A string of doublewords is given. Order in decreasing order the string of the low words (least significant) from these doublewords. The high words (most significant) remain unchanged.
;Example:
;being given sir DD 12345678h, 1256ABCDh, 12AB4344h
;the result will be 1234ABCDh, 12565678h, 12AB4344h.

bits 32
global start
extern exit
import exit msvcrt.dll
segment data use32 class=data
	sir  dd  12345678h,1256ABCDh, 12AB4344h
	len equ ($-sir)/4;the length of the string (in doublewords)
	d resb len

segment code use32 class=code
    start:
	mov esi, sir;in eds:esi we will store the FAR address of the string "sir"    
    mov edi,d
    cld
    mov ecx, len-1;we will parse the elements of the string in a loop with len iterations.
    repeta:
        lodsd;we take the current doubleword from the string
        mov ebx,eax; we store the current double word in ebx
        lodsd;we take the next doubleword from the string
        cmp ax, bx;we compare the low words of the doublewords
        jl swap;if the first is less than the second, we swap them
        stosd;otherwise, we put the current doubleword in the string "d",unchanged
        jmp next;and we go to the next iteration
    swap:
        xchg bx,ax
        mov eax,ebx
        stosd;we put the current doubleword  in the string "d", but with the lower word equal to the next doubleword
    next:
        loop repeta;we go to the next iteration
    mov eax, 0
    push dword 0; push the parameter for exit onto the stack
    call [exit]; call exit