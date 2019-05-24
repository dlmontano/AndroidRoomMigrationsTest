package com.pammos.roommigrationtest.repository;

import com.pammos.roommigrationtest.dao.BaseDao;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseRepository<T> {

    void save(BaseDao baseDao, T object) {
        Completable.fromAction(() -> baseDao.save(object))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {

                        disposable = d;
                    }

                    @Override
                    public void onComplete() {

                        disposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (disposable != null) {

                            disposable.dispose();
                        }
                    }
                });
    }

    void save(BaseDao baseDao, T... objects) {
        Completable.fromAction(() -> baseDao.delete(objects))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {

                        disposable = d;
                    }

                    @Override
                    public void onComplete() {

                        disposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (disposable != null) {

                            disposable.dispose();
                        }
                    }
                });
    }

    void update(BaseDao baseDao, T object) {
        Completable.fromAction(() -> baseDao.update(object))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {

                        disposable = d;
                    }

                    @Override
                    public void onComplete() {

                        disposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (disposable != null) {

                            disposable.dispose();
                        }
                    }
                });
    }

    void delete(BaseDao baseDao, T object) {
        Completable.fromAction(() -> baseDao.delete(object))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {

                        disposable = d;
                    }

                    @Override
                    public void onComplete() {

                        disposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (disposable != null) {

                            disposable.dispose();
                        }
                    }
                });
    }

    void delete(BaseDao baseDao, T... objects) {
        Completable.fromAction(() -> baseDao.delete(objects))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {

                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {

                        disposable = d;
                    }

                    @Override
                    public void onComplete() {

                        disposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {

                        if (disposable != null) {

                            disposable.dispose();
                        }
                    }
                });
    }
}
