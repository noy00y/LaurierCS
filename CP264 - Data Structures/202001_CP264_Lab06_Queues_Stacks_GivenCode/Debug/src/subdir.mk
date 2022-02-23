################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/Lab06_main.c \
../src/name_list.c \
../src/utility_library.c 

OBJS += \
./src/Lab06_main.o \
./src/name_list.o \
./src/utility_library.o 

C_DEPS += \
./src/Lab06_main.d \
./src/name_list.d \
./src/utility_library.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: Cygwin C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


