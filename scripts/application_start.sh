#!/bin/bash
set -e
export PATH="/usr/bin:/bin:$PATH"

echo "=== [APPLICATION_START] 커피빵 게임 서버 시작 ==="

cd /opt/LV3_final

# ==========================================
# 1단계: Spring Boot JAR 애플리케이션 시작
# ==========================================
echo "☕ 1. Spring Boot 애플리케이션 시작 중..."

ls -a

# JAR 파일 확인
if [ -f "LV3_final.jar" ]; then
    echo "   📄 JAR 파일 확인됨: LV3_final.jar"
else
    echo "   ❌ JAR 파일을 찾을 수 없습니다!"
    exit 1
fi

# 기존 JAR 프로세스 종료 (있다면)
if [ -f "LV3_final.pid" ]; then
    OLD_PID=$(cat LV3_final.pid)
    if ps -p $OLD_PID > /dev/null 2>&1; then
        echo "   🛑 기존 애플리케이션 프로세스 종료 중 (PID: $OLD_PID)"
        kill -SIGTERM $OLD_PID
        sleep 5

        # 강제 종료가 필요한 경우
        if ps -p $OLD_PID > /dev/null 2>&1; then
            kill -SIGKILL $OLD_PID
        fi
    fi
    rm -f LV3_final.pid
fi

# JVM 옵션 설정
JVM_OPTS="-Xms512m -Xmx1024m"
JVM_OPTS="$JVM_OPTS -XX:+UseG1GC"
JVM_OPTS="$JVM_OPTS -XX:+PrintGCDetails"
JVM_OPTS="$JVM_OPTS -Xloggc:logs/gc.log"
JVM_OPTS="$JVM_OPTS -Duser.timezone=Asia/Seoul"

# Spring Boot 애플리케이션 실행 (8080 포트)
echo "   🚀 Spring Boot 애플리케이션 시작 중..."
nohup java $JVM_OPTS \
    -jar app/LV3_final-backend.jar \
    > logs/application.log 2>&1 &

# PID 저장
echo $! > LV3_final.pid
echo "   ✅ Spring Boot 애플리케이션 시작 완료 (PID: $(cat LV3_final.pid))"
